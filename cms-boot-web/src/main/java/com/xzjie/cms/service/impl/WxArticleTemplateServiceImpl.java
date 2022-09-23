package com.xzjie.cms.service.impl;

import cn.hutool.core.util.ReUtil;
import com.google.common.collect.Lists;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.core.utils.JsonUtils;
import com.xzjie.cms.dto.*;
import com.xzjie.cms.enums.MassMsgType;
import com.xzjie.cms.model.WxAccountFans;
import com.xzjie.cms.model.WxArticle;
import com.xzjie.cms.model.WxArticleTemplate;
import com.xzjie.cms.core.persistence.SpecSearchCriteria;
import com.xzjie.cms.repository.WxArticleRepository;
import com.xzjie.cms.repository.WxArticleTemplateRepository;
import com.xzjie.cms.service.WechatService;
import com.xzjie.cms.service.WxAccountFansService;
import com.xzjie.cms.service.WxArticleTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WxArticleTemplateServiceImpl extends AbstractService<WxArticleTemplate, WxArticleTemplateRepository> implements WxArticleTemplateService {

    @Autowired
    private WxArticleRepository articleRepository;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private WxAccountFansService accountFansService;


//    @Override
//    public boolean update(WxArticleTemplate obj) {
//        WxArticleTemplate model = baseRepository.getOne(obj.getId());
//        model.copy(obj);
//        baseRepository.save(model);
//        return false;
//    }

    @Override
    public WxArticleTemplate getArticleTemplate(Long id) {
        return baseRepository.getOne(id);
    }


    @Override
    public List<WxArticleTemplate> getArticleTemplate(WxArticleTemplate query) {
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query == null) {
                return null;
            }
            if (null != query.getTemplateName()) {
                predicates.add(criteriaBuilder.equal(root.get("templateName").as(String.class), query.getTemplateName()));
            }
            if (null != query.getPublish()) {
                predicates.add(criteriaBuilder.equal(root.get("publish").as(Boolean.class), query.getPublish()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
    }

    @Override
    public Page<WxArticleTemplate> getArticleTemplate(WxArticleTemplateQueryDto query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize(), Sort.by("id").descending());
        Specification<WxArticleTemplate> specification = SpecSearchCriteria.builder(query);
        return baseRepository.findAll(specification, pageable);
    }

    @Override
    public List<WxArticle> getArticle(String newsId) {
        return articleRepository.findByNewsId(newsId);
    }

    @Override
    public void batchDeleteArticle(List<String> list) {
        List<WxArticle> articles = Lists.newArrayList();
        list.stream().forEach(id -> {
            WxArticle article = new WxArticle();
            article.setId(Long.parseLong(id));
        });
        articleRepository.deleteInBatch(articles);
    }

    @Override
    public void saveArticle(String newsId, List<WxArticle> list, List<String> articleIds) {
        // 删除
        if (articleIds.size() > 0) {
            this.batchDeleteArticle(articleIds);
        }

        for (int i = 0; i < list.size(); i++) {
            WxArticle article = list.get(i);
            article.setShowCoverPic("0");
            article.setOrderNo(i);
            article.setNewsId(newsId);
            if (article.getId() == null) {
                articleRepository.save(article);
            } else {
                WxArticle model = articleRepository.findById(article.getId()).orElseGet(WxArticle::new);

                model.copy(article);
                //如果图片地址发生变化,ThumbMediaId重置，上传微信的时候，根据该字段，判断是否需要上传
                if (!article.getImage().equals(model.getImage())) {
                    model.setThumbMediaId("");
                }
                articleRepository.save(model);
            }
        }
    }

    @Override
    public void updateArticle(String newsId, List<WxArticle> list, List<String> articleIds) {
        WxArticleTemplate articleTemplate = baseRepository.findById(Long.parseLong(newsId)).orElseGet(WxArticleTemplate::new);

        this.saveArticle(newsId, list, articleIds);

        WxMediaArticle mediaArticle = WxMediaArticle.builder();
        for (int i = 0; i < list.size(); i++) {
            WxArticle article = list.get(i);

            // 上传微信材料资源
            WxMediaUploadResult mediaUploadResult = wechatService.addMedia(article.getImage());
            article.setThumbMediaId(mediaUploadResult.getMediaId());
            articleRepository.updateThumbMediaId(mediaUploadResult.getMediaId(), article.getId());

            //处理content
            String content = processContent(article.getContent());
            mediaArticle.add(WxMediaArticle.getItemMap()
                    .add("title", article.getTitle())
                    .add("thumb_media_id", article.getThumbMediaId())
                    .add("author", article.getAuthor())
                    .add("digest", article.getDigest())
                    .add("show_cover_pic", article.getShowCoverPic())
                    .add("content", content)
                    .add("content_source_url", article.getContentSourceUrl()));
            // 注意，测试号没有留言权限
//                    .add("need_open_comment", article.getNeedOpenComment())
//                    .add("only_fans_can_comment", article.getOnlyFansCanComment()));
        }

        String json = mediaArticle.build();
        log.info(">> wechat media article: {}", json);
        WxMediaUploadResult mediaUploadResult = wechatService.addMediaArticle(json);
        log.info(">>wechat media article result: {}", JsonUtils.toJsonString(mediaUploadResult));


        articleTemplate.setPublish(true);
        articleTemplate.setMediaId(mediaUploadResult.getMediaId());

        this.save(articleTemplate);
    }

    @Override
    public void sendPreviewArticleTemplate(WxArticleTemplate articleTemplate, List<Long> fansIds) {
        WxArticleTemplate model = baseRepository.getOne(articleTemplate.getId());
        for (Long fansId : fansIds) {
            WxAccountFans accountFans = accountFansService.getAccountFans(fansId);
            WxMessagePreview messageData = WxMessagePreview.builder().addMediaId(model.getMediaId());
            messageData.setMsgtype(MassMsgType.mpnews.name());
            messageData.setTouser(accountFans.getOpenId());
            wechatService.messagePreview(messageData.build());
        }
    }

    @Override
    public void sendTagArticleTemplate(WxArticleTemplate articleTemplate, Long tagId) {
        WxArticleTemplate model = baseRepository.getOne(articleTemplate.getId());
        WxMessageTag messageTag = WxMessageTag.builder()
                .setMsgtype(MassMsgType.mpnews.name())
                .addMediaId(model.getMediaId())
                .setAll(false)
                .setTagId(tagId);
        wechatService.messageTag(messageTag.build());
    }

    @Override
    public void sendFansArticleTemplate(WxArticleTemplate articleTemplate, List<Long> fansIds) {
        WxArticleTemplate model = baseRepository.getOne(articleTemplate.getId());
        WxMessage message = WxMessage.builder()
                .addMediaId(model.getMediaId()).setMsgtype(MassMsgType.mpnews.name());
        for (Long fansId : fansIds) {
            WxAccountFans accountFans = accountFansService.getAccountFans(fansId);
            message.addOpenId(accountFans.getOpenId());
        }
        wechatService.message(message.build());
    }

//    private String [] imgBitsDeal(byte[]bits, String prefix){
//        String [] rt = new String[2];
//        // snippet for JMimeMagic lib
//        // http://sourceforge.net/projects/jmimemagic/
//        Magic parser = new Magic() ;
//        MagicMatch match = null;
//        try {
//            match = parser.getMagicMatch(bits);
//            rt[1] = match.getMimeType();//文件类型
//            rt[0] = prefix + "." + match.getExtension();//构造文件名称（含扩展名）
////			System.out.println(match.getMimeType()) ;
////			System.out.println(match.getExtension()) ;
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            rt[0] = prefix + "." + "png";	//默认文件名
//            rt[1] = "image/png";	//默认文件类型
//            e.printStackTrace();
//        }
//
//        return rt;
//    }

    private String getFilePath() {
        LocalDateTime localDate = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        //4.把 2019-01-01  转成  2019/01/01
        String format = localDate.format(dtf);
        return "wechat/" + format + "/" + System.currentTimeMillis() +
                RandomUtils.nextInt(4) + ".png";
    }

//    private WxMediaUploadResult uploadPhotoToWx(String image) throws WxErrorException {
//        WxMpMaterial wxMpMaterial = new WxMpMaterial();
//
////        String filePath = getFilePath();
////        String imagePath = localProperties.getPath() + filePath;
//
////        File imageFile = FileUtil.file(imagePath);
//        byte[] bytes = HttpUtils.doDownload(image);
//
////        wxMpMaterial.setFile(imageFile);
////        wxMpMaterial.setName(imageFile.getName());
////        log.info("picFile name : {}", imageFile.getName());
////        WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpService.getMaterialService().materialFileUpload(WxConsts.MediaFileType.IMAGE, wxMpMaterial);
//
//        WxMediaUploadResult mediaUploadResult = wechatService.addMedia(MediaFileType.image, bytes);
//        log.info("wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr(mediaUploadResult));
//        return mediaUploadResult;
//    }

    private String processContent(String content) {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        String imgReg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        List<String> imgList = ReUtil.findAllGroup1(imgReg, content);
        for (int j = 0; j < imgList.size(); j++) {
            String image = imgList.get(j);
            WxMediaUploadResult mediaUploadResult = wechatService.addMedia(image);
            content = StringUtils.replace(content, imgList.get(j), mediaUploadResult.getUrl());
        }
        return content;
    }
}
