package com.xzjie.cms.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Lists;
import com.xzjie.cms.configure.LocalProperties;
import com.xzjie.cms.core.service.AbstractService;
import com.xzjie.cms.model.WxArticle;
import com.xzjie.cms.model.WxArticleTemplate;
import com.xzjie.cms.repository.WxArticleRepository;
import com.xzjie.cms.repository.WxArticleTemplateRepository;
import com.xzjie.cms.service.WxArticleTemplateService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriUtils;

import javax.persistence.criteria.Predicate;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class WxArticleTemplateServiceImpl extends AbstractService<WxArticleTemplate, Long> implements WxArticleTemplateService {

    @Autowired
    private WxArticleTemplateRepository articleTemplateRepository;

    @Autowired
    private WxArticleRepository articleRepository;
    @Autowired
    private WechatService wechatService;
    @Autowired
    private LocalProperties localProperties;

    @Override
    protected JpaRepository getRepository() {
        return articleTemplateRepository;
    }

    @Override
    public boolean update(WxArticleTemplate obj) {
        return false;
    }

    @Override
    public WxArticleTemplate getArticleTemplate(Long id) {
        return articleTemplateRepository.getOne(id);
    }

    @Override
    public Page<WxArticleTemplate> getArticleTemplate(Integer page, int size, WxArticleTemplate query) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return articleTemplateRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (query == null) {
                return null;
            }
            if (null != query.getTemplateName()) {
                predicates.add(criteriaBuilder.equal(root.get("templateName").as(String.class), query.getTemplateName()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
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
    public void updateArticle(String newsId, List<WxArticle> list, List<String> articleIds) throws IOException, WxErrorException {
        WxArticleTemplate articleTemplate = articleTemplateRepository.findById(Long.parseLong(newsId)).orElseGet(WxArticleTemplate::new);

        this.saveArticle(newsId, list, articleIds);

        WxMpService wxMpService = wechatService.create();
        WxMpMaterialNews materialNews = new WxMpMaterialNews();
        for (int i = 0; i < list.size(); i++) {
            WxArticle article = list.get(i);

//            WxMpMaterialUploadResult wxMpMaterialUploadResult = uploadPhotoToWx(wxMpService, article.getImage());
//            article.setThumbMediaId(wxMpMaterialUploadResult.getMediaId());
            article.setThumbMediaId("XQRUgXjSqS1YbqZHwmoWWUsxJJJu5DTr7w4tamvsuh0");
            // XQRUgXjSqS1YbqZHwmoWWcEe5S-Zcvlrj9zAQJ-alfg

            // 更新 ThumbMediaId
            articleRepository.updateThumbMediaId("XQRUgXjSqS1YbqZHwmoWWUsxJJJu5DTr7w4tamvsuh0", article.getId());
//            articleRepository.updateThumbMediaId(wxMpMaterialUploadResult.getMediaId(), article.getId());

            WxMpMaterialNews.WxMpMaterialNewsArticle newsArticle = new WxMpMaterialNews.WxMpMaterialNewsArticle();

            //处理content
            String content = processContent(wxMpService, article.getContent());
            newsArticle.setContent(content);
            newsArticle.setContentSourceUrl(article.getContentSourceUrl());
            newsArticle.setShowCoverPic(article.getShowCoverPic().equals("1"));
            newsArticle.setThumbMediaId(article.getThumbMediaId());

            newsArticle.setTitle(article.getTitle());
            newsArticle.setAuthor(article.getAuthor());
            newsArticle.setDigest(article.getDigest());
            // 注意，测试号没有留言权限
//            newsArticle.setNeedOpenComment(article.getNeedOpenComment().equals("1") ? true : false);
//            newsArticle.setOnlyFansCanComment(article.getOnlyFansCanComment().equals("1") ? true : false);
            materialNews.addArticle(newsArticle);
        }

        log.info("wxMpMaterialNews : {}", JSONUtil.toJsonStr(materialNews));
//        WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpService.getMaterialService().materialNewsUpload(materialNews);
//        log.info("wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr(wxMpMaterialUploadResult));


        articleTemplate.setPublish(true);
//        articleTemplate.setMediaId(wxMpMaterialUploadResult.getMediaId());
        articleTemplate.setMediaId("XQRUgXjSqS1YbqZHwmoWWWr3OYNZa0bgsxUnYdwX5Is");

        this.save(articleTemplate);
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

    private WxMpMaterialUploadResult uploadPhotoToWx(WxMpService wxMpService, String image) throws WxErrorException {
        WxMpMaterial wxMpMaterial = new WxMpMaterial();

        String filePath = getFilePath();
        String imagePath = localProperties.getPath() + filePath;

        File imageFile = FileUtil.file(imagePath);
        long size = HttpUtil.downloadFile(image, imageFile);

        wxMpMaterial.setFile(imageFile);
        wxMpMaterial.setName(imageFile.getName());
        log.info("picFile name : {}", imageFile.getName());
        WxMpMaterialUploadResult wxMpMaterialUploadResult = wxMpService.getMaterialService().materialFileUpload(WxConsts.MediaFileType.IMAGE, wxMpMaterial);
        log.info("wxMpMaterialUploadResult : {}", JSONUtil.toJsonStr(wxMpMaterialUploadResult));
        return wxMpMaterialUploadResult;
    }

    private String processContent(WxMpService wxMpService, String content) throws IOException, WxErrorException {
        if (StringUtils.isBlank(content)) {
            return content;
        }
        String imgReg = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
        List<String> imgList = ReUtil.findAllGroup1(imgReg, content);
        for (int j = 0; j < imgList.size(); j++) {
            String imgSrc = imgList.get(j);
            String fileType = imgSrc.substring(imgSrc.lastIndexOf("."));
            String DEFAULT_CHARSET = "UTF-8";
            URL url = new URL(UriUtils.encodePath(imgSrc, DEFAULT_CHARSET));
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "plain/text;charset=" + DEFAULT_CHARSET);
            conn.setRequestProperty("charset", DEFAULT_CHARSET);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
//            conn.setReadTimeout(DEFAULT_TIME_OUT);
            conn.connect();
            InputStream is = conn.getInputStream();

            WxMediaUploadResult wxMediaUploadResult = wxMpService.getMaterialService().mediaUpload("image", fileType, is);

//            if (StringUtils.isBlank(filepath)) { // 网络图片URL，需下载到本地
//                String filename = String.valueOf(System.currentTimeMillis()) + ".png";
//                String downloadPath = uploadDirStr + filename;
//                long size = HttpUtil.downloadFile(imgSrc, FileUtil.file(downloadPath));
//                filepath = downloadPath;
//            }
//            WxMediaImgUploadResult wxMediaImgUploadResult = wxMpService.getMaterialService().mediaImgUpload(new File(filepath));
            content = StringUtils.replace(content, imgList.get(j), wxMediaUploadResult.getUrl());
        }
        return content;
    }
}
