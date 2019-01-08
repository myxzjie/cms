package com.xzjie.et.wechat.entity;

import java.util.List;

public class SenderContent {

    /**
     * @Fields mpnews : TODO 通过media_id发送图文消息
     */
    private Media mpnews;
    /**
     * @Fields news : TODO 修改 增加 通过Article发送图文消息
     */
    private News news;

    public Media getMpnews() {
        return mpnews;
    }
    public void setMpnews(Media mpnews) {
        this.mpnews = mpnews;
    }

    public News getNews() {
        return news;
    }
    public void setNews(News news) {
        this.news = news;
    }

    /**
     * 媒体包括图文语音视频图片
     */
    public static class Media{
        private String media_id;
        public Media() {
        }
        public Media(String media_id) {
            this.media_id = media_id;
        }
        public String getMedia_id() {
            return media_id;
        }

        public void setMedia_id(String media_id) {
            this.media_id = media_id;
        }


    }
    public static class News{
        private List<Article> articles;
        public News() {
        }
        public News(List<Article> articles) {
            this.articles = articles;
        }
        public List<Article> getArticles() {
            return articles;
        }
        public void setArticles(List<Article> articles) {
            this.articles = articles;
        }
    }
    public static class Article{
        private String description;
        private String title;
        private String url;
        private String picurl;
        public Article() { }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
        public String getUrl() {
            return url;
        }
        public void setUrl(String url) {
            this.url = url;
        }
        public String getPicurl() {
            return picurl;
        }
        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }
    }
}
