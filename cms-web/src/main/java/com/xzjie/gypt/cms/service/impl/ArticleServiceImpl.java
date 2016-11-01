package com.xzjie.gypt.cms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xzjie.gypt.cms.dao.ArticleContentMapper;
import com.xzjie.gypt.cms.dao.ArticleMapper;
import com.xzjie.gypt.cms.model.Article;
import com.xzjie.gypt.cms.model.ArticleContent;
import com.xzjie.gypt.cms.service.ArticleService;
import com.xzjie.gypt.common.page.Page;
import com.xzjie.gypt.common.page.PageEntity;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

	@Autowired
	private ArticleMapper articleMapper;
	@Autowired
	private ArticleContentMapper articleContentMapper;

	@Override
	public boolean save(Article record) {

		record.setCreateDate(new Date());
		record.setStatus(1); // 1 正常 0失效

		return articleMapper.insert(record) > 0;
	}

	@Override
	public void batchSave(List<Article> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean update(Article record) {
		
		return articleMapper.update(record) > 0;
	}

	@Override
	public void batchUpdate(List<Article> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDeleteById(List<Long> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(Article record) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void batchDelete(List<Article> records) {
		// TODO Auto-generated method stub

	}

	@Override
	public Article get(Long id) {
		return articleMapper.getById(id);
	}

	@Override
	public Article get(Article record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getList(Article record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getAllList(Article record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getListPage(PageEntity<Article> record) {
		return articleMapper.findListPage(record);
	}

	@Override
	public void save(Article record, String content) {
		ArticleContent articleContent = new ArticleContent();

		this.save(record);

		articleContent.setArticleId(record.getArticleId());
		articleContent.setContent(content);
		articleContentMapper.insert(articleContent);
	}

	@Override
	public ArticleContent getContent(Long id) {
		return articleContentMapper.getById(id);
	}

	@Override
	public void update(Article record, String content) {
		
		ArticleContent articleContent= new ArticleContent();
		articleContent.setArticleId(record.getArticleId());
		articleContent.setContent(content);
		this.update(record);
		articleContentMapper.updateByArticleId(articleContent);
		
	}

	@Override
	public List<Article> sliderList(Article record) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Article> getContentBySiteId(Long siteId) {
		PageEntity<Article> record = new PageEntity<Article>();
		Page page=new Page();
		Article article = new Article();
		
		page.setCurrentPage(1);
		
		article.setSiteId(siteId);
		
		record.setPage(page);
		record.setRecord(article);
		
		return articleMapper.findListPage(record);
	}

}
