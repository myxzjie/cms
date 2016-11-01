<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
		<c:forEach var="article" items="${articles}">
		<article class="am-g blog-entry-article">
            <div class="am-u-lg-4 am-u-md-12 am-u-sm-12 blog-entry-img">
                <img src="${uploadImageWeb}${article.image}" alt="" class="thumbnails am-u-sm-12">
            </div>
            <div class="am-u-lg-8 am-u-md-12 am-u-sm-12 blog-entry-text">
            	<h1><a href="${ctx_front}/article?cid=${siteId}&id=${article.articleId}">${article.title}</a></h1>
                <!-- <span><a href="" class="blog-color">article &nbsp;</a></span> -->
                
                <span> <i class="am-icon-comment blog-color"></i>&nbsp; ${article.countComment} &nbsp;</span>
                <span> <i class="am-icon-link blog-color"></i>&nbsp; ${article.countView} &nbsp;</span>
                <span> ${article.authorName} &nbsp;</span>
                
                <span><fmt:formatDate value="${article.createDate}" pattern="yyyy/MM/dd" /></span>
                
                <p class="description">${article.description}</p>
                <p><a href="" class="blog-continue">continue reading</a></p>
            </div>
        </article>
		</c:forEach>