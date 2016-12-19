<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!doctype html>
<html>
<head>
  <%@ include file="include/meta.jsp" %>
  <title>${model.title}-${site.siteName}</title>
  <meta name="description" content="${model.keywords}">
  <meta name="keywords" content="${model.description}">
  <%@ include file="include/style.jsp" %>
</head>
<body>
<div class="am-container " id="main">
				
	  <div class="am-g am-g-fixed">
	    <div class="am-u-md-8">
		    <ol class="am-breadcrumb">
			  <li><a href="/" class="am-icon-home">首页</a></li>
			  <li><a href="/blog">博客</a></li>
			  <li class="am-active">我的博客</li>
			</ol>
			
		  	
    

  	
  	
	  	<div class="inner-box blog-img ">
			<div class="blog-header">
				<a href="/blog/categorySearcher?category=1" class="blog-category">Java综合</a>
				<h2><a href="/blog/detail/181">d</a></h2>
			</div>
			<p class="blog-ext">
				<span class="blog-ext-ico">
					<i class="am-icon-user  blog-ext-ico"></i>
					xzjie
				</span>
				<span class="blog-ext-ico"><i class="am-icon-clock-o  blog-ext-ico"></i>2016-12-04 20:34:26</span>
				<span class="blog-ext-ico">
					<i class="am-icon-eye  blog-ext-ico"></i>
					0
				</span>
			</p>
			<p class="blog-content-show">d...</p>
			<div class="am-fr">
			<button type="button" class="am-badge am-badge-danger am-round" onclick="deleteblog(181)">删除</button>
			<button type="button" class="am-badge am-badge-success am-round" onclick="location.href='/blog/edit/181'">修改</button>
			</div>
 		</div>
  	

  	
  	
	  	<div class="inner-box blog-img ">
			<div class="blog-header">
				<a href="/blog/categorySearcher?category=1" class="blog-category">Java综合</a>
				<h2><a href="/blog/detail/180">s</a></h2>
			</div>
			<p class="blog-ext">
				<span class="blog-ext-ico">
					<i class="am-icon-user  blog-ext-ico"></i>
					xzjie
				</span>
				<span class="blog-ext-ico"><i class="am-icon-clock-o  blog-ext-ico"></i>2016-12-04 20:33:55</span>
				<span class="blog-ext-ico">
					<i class="am-icon-eye  blog-ext-ico"></i>
					0
				</span>
			</p>
			<p class="blog-content-show">s...</p>
			<div class="am-fr">
			<button type="button" class="am-badge am-badge-danger am-round" onclick="deleteblog(180)">删除</button>
			<button type="button" class="am-badge am-badge-success am-round" onclick="location.href='/blog/edit/180'">修改</button>
			</div>
 		</div>
  	

			
			
			
			
			<center>
				
    


<link rel="stylesheet" href="/css/pagination.css">


	
	
		
	
	
	
		
	
	<div class="pagination pagination-lg">
	<ul>
		
			
		
		
		
			
		
		
		
			
				  <li>
				  	<a rel="prev" class="disabled">上一页</a>
				  </li>
			
			
		
		
		
		
		
			
				
					<li class="active">
						<span class="current">1</span>
					</li>
				
				
			
		
		
		
		
		
			
				  <li>
				    <a class="disabled">下一页</a>
				  </li>
			
			
		
	</ul>
	</div>

			</center>
	    	
	    </div>
	    <div class="am-u-md-4" id="my-side">
	    	
		    <div class="side-box">
		    	<div class="my-side-title">
					<span class="am-icon-pencil"> 功能区</span> 
		    	</div>
	    		<button type="button" class="am-btn am-btn-primary am-btn-block" onclick="location.href='/blog/add'">
		    		<i class="am-icon-pencil"></i>
		    		写博客
				</button> 
		    	<button type="button" class="am-btn am-btn-primary am-btn-block" onclick="location.href='/blog/myblog'">
		    		<i class="am-icon-user"></i>
		    		我的博客
				</button>
			</div>
			
		    	


<script src="/js/jquery.scrollbox.js"></script>
<style type="text/css">
.taglist {
	padding: 20px 20px 30px 20px;
}

.taglist a {
	padding: 3px;
	display: inline-block;
	white-space: nowrap;
}

a.size1 {
	font-size: 20px;
	padding: 10px;
	color: #804D40;
}

a.size1:hover {
	color: #E13728;
}

a.size2 {
	padding: 7px;
	font-size: 20px;
	color: #B9251A;
}

a.size2:hover {
	color: #E13728;
}

a.size3 {
	padding: 5px;
	font-size: 26px;
	color: #C4876A;
}

a.size3:hover {
	color: #E13728;
}

a.size4 {
	padding: 5px;
	font-size: 15px;
	color: #B46A47;
}

a.size4:hover {
	color: #E13728;
}

a.size5 {
	padding: 5px;
	font-size: 25px;
	color: #E13728;
}

a.size5:hover {
	color: #B46A47;
}

a.size6 {
	padding: 0px;
	font-size: 12px;
	color: #77625E
}

a.size6:hover {
	color: #E13728;
}

a.size7 {
	padding: 0px;
	font-size: 20px;
	color: #FFCCFF
}

a.size7:hover {
	color: #FF6633;
}

.scroll-text {
	width: 100%;
	height: 14em;
	overflow: hidden;
	color: black !important;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	
	/*多彩tag*/
	var tags_a = $("#tagscloud").find("a");
	tags_a.each(function(){
		var x = 9;
		var y = 0;
		var rand = parseInt(Math.random() * (x - y + 1) + y);
		$(this).addClass("size"+rand);
	});
});

</script>
<!--用户登陆信息  -->

	<div class="side-box" id="side-userinfo-parent">
		<div class="my-side-title">
			<span class="am-icon-user"> 用户信息</span>
		</div>
		<div class="side-userinfo">
			
			
				<img alt="xzjie" src="/attached/avatar/default.jpg" >
			
			<div class="side-userinfo-info" id="side-userinfo-son">
				<span class="side-userinfo-title">用户名：</span><span class="side-userinfo-name">xzjie</span><br>
				<span class="side-userinfo-title">昵称：</span><span class="side-userinfo-name">xzjie</span><br>
				<a href="/logoutside" class="am-icon-power-off side-userinfo-logout"> 注销登陆</a>
			</div>
		</div>
	</div>


<!-- 站内搜索 -->
<div class="side-box">
	<div class="my-side-title">
		<span class="am-icon-search"> 站内搜索</span>
	</div>
	<div>
		<form class="" action="/index/search/" method="get">
		<div class="am-input-group am-input-group-danger">
	      <input type="text" name="keyword" value="" class="am-form-field" placeholder="搜索..." required="">
	      <span class="am-input-group-btn">
	        <button class="am-btn am-btn-lg am-btn-danger" type="submit"><span class="am-icon-search"></span></button>
	      </span>
	      </div>
	    </form>
	    
	</div>
</div>

<div class="side-box">
	<div class="my-side-title">
		<span class="am-icon-search"> CMS...</span>
	</div>
	<div>
		<button type="button" onclick="location.href='http://www.lovepanda.top'" class="am-btn am-btn-primary am-btn-block">CMS测试版入口</button>
	</div>
</div>
<!-- 博客标签云 -->
<div class="side-box">
	<div class="my-side-title">
		<span class="am-icon-tag"> 博客标签云</span>
	</div>
	<div id="tagscloud">
		
			<a href="/blog/categorySearcher?category=1" class="tagc0 size9">Java综合</a>
		
			<a href="/blog/categorySearcher?category=2" class="tagc1 size9">JavaSE</a>
		
			<a href="/blog/categorySearcher?category=3" class="tagc2 size6">JavaEE</a>
		
			<a href="/blog/categorySearcher?category=4" class="tagc0 size3">JavaME</a>
		
			<a href="/blog/categorySearcher?category=5" class="tagc1 size4">Python</a>
		
			<a href="/blog/categorySearcher?category=6" class="tagc2 size1">C语言</a>
		
			<a href="/blog/categorySearcher?category=7" class="tagc0 size8">C++</a>
		
			<a href="/blog/categorySearcher?category=8" class="tagc1 size3">C#</a>
		
			<a href="/blog/categorySearcher?category=9" class="tagc2 size6">MySQL</a>
		
			<a href="/blog/categorySearcher?category=10" class="tagc0 size8">oracle</a>
		
			<a href="/blog/categorySearcher?category=11" class="tagc1 size2">MSSQL</a>
		
			<a href="/blog/categorySearcher?category=12" class="tagc2 size7">JavaScript</a>
		
			<a href="/blog/categorySearcher?category=13" class="tagc0 size5">jQuery</a>
		
			<a href="/blog/categorySearcher?category=14" class="tagc1 size3">EasyUI</a>
		
			<a href="/blog/categorySearcher?category=15" class="tagc2 size8">HTML</a>
		
			<a href="/blog/categorySearcher?category=16" class="tagc0 size4">HTML5</a>
		
			<a href="/blog/categorySearcher?category=17" class="tagc1 size8">CSS</a>
		
			<a href="/blog/categorySearcher?category=18" class="tagc2 size1">PHP</a>
		
			<a href="/blog/categorySearcher?category=19" class="tagc0 size5">Windows</a>
		
			<a href="/blog/categorySearcher?category=20" class="tagc1 size0">Linux</a>
		
			<a href="/blog/categorySearcher?category=21" class="tagc2 size7">Unix</a>
		
			<a href="/blog/categorySearcher?category=22" class="tagc0 size8">笔记</a>
		
			<a href="/blog/categorySearcher?category=23" class="tagc1 size6">随笔</a>
		
			<a href="/blog/categorySearcher?category=24" class="tagc2 size6">娱乐</a>
		
			<a href="/blog/categorySearcher?category=25" class="tagc0 size9">搞笑</a>
		
			<a href="/blog/categorySearcher?category=26" class="tagc1 size1">程序猿</a>
		
			<a href="/blog/categorySearcher?category=27" class="tagc2 size4">其他</a>
		
	</div>
</div>
<a href="http://s.click.taobao.com/t?e=m%3D2%26s%3D2lp8ZOlsbh8cQipKwQzePCperVdZeJviEViQ0P1Vf2kguMN8XjClAuEc3mx55Ht5jTj4KK5tHIPQV%2FVTErx%2FQQrV1twKDMTtA1iwpvkxBnvizI5WrLAhtxmIkXBqRClNTcEU%2BDykfuTlSg55GVX5wb6HrfO5Rkxh34mdTsZIUcAD%2Bi4rDfTRpeTIM5d0rdP%2B4UsysfR7j9ghhQs2DjqgEA%3D%3D" target="_blank"> <img src="/images/aliyun.gif" style="margin-bottom: 15px;"></a>

	<div class="side-box">
		<div class="my-side-title">
			<span><i class="am-icon-spinner am-icon-spin"></i> 滚动播报</span>
		</div>
		<div id="demo2" class="scroll-text">
			<ul>
				
					
				
					<li><span class="am-badge am-badge-secondary am-round">2</span>&nbsp;<a href="#">大家可以登陆发布博客，美图或视频，不限题材。</a></li>
				
					<li><span class="am-badge am-badge-secondary am-round">3</span>&nbsp;<a href="#">博主最近很忙，最近主要会分享一些好的博文给大家。</a></li>
				
					<li><span class="am-badge am-badge-secondary am-round">4</span>&nbsp;<a href="http://www.liuyunfei.cn/blog/detail/120">Java面试宝典</a></li>
				
					<li><span class="am-badge am-badge-secondary am-round">5</span>&nbsp;<a href="#">大家多点点两侧的广告，为博主减轻点服务器费用</a></li>
				
					<li><span class="am-badge am-badge-secondary am-round">6</span>&nbsp;<a href="#">发现任何bug请及时反馈，我会及时查看和修复。</a></li>
				
			<li><span class="am-badge am-badge-secondary am-round">1</span>&nbsp;<a href="http://www.liuyunfei.cn/blog/detail/109"><span style="color:red">LovePanda博客源码已经发布</span></a></li></ul>
		</div>
	</div>

<div class="side-box" style="margin-top: 15px">
	<div class="am-tabs" data-am-tabs="">
		<ul class="am-tabs-nav am-nav am-nav-tabs" style="margin-left: 0px;">
			<li class="am-active"><a href="#tab1">热评文章</a></li>
			<li><a href="#tab2">最新评论</a></li>
			<li><a href="#tab3">最近访客</a></li>
		</ul>

		<div class="am-tabs-bd" style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
			<div class="am-tab-panel am-fade am-in am-active" id="tab1">
				<!-- 多说热评文章 start -->
				<div class="ds-top-threads" data-range="monthly" data-num-items="5"><div id="ds-top-threads"><li><a target="_blank" href="http://www.liuyunfei.cn/liuyanban/index" title="LovePanda的留言板">LovePanda的留言板</a></li><li><a target="_blank" href="http://www.liuyunfei.cn/blog/detail/92" title="sigar的使用入门">sigar的使用入门</a></li><li><a target="_blank" href="http://www.liuyunfei.cn/blog/detail/173" title="idea自动生成serialVersionUID">idea自动生成serialVersionUID</a></li><li><a target="_blank" href="http://www.liuyunfei.cn/blog/detail/178" title="lovepanda603先生">lovepanda603先生</a></li><li><a target="_blank" href="http://www.liuyunfei.cn/blog/detail/179" title="lovepanda603先生">lovepanda603先生</a></li></div></div>
				<!-- 多说热评文章 end -->
			</div>
			<div class="am-tab-panel am-fade" id="tab2">
				<!-- 多说最新评论 start -->
				<div class="ds-recent-comments" data-num-items="5" data-show-avatars="1" data-show-time="1" data-show-title="1" data-show-admin="1" data-excerpt-length="70"><div id="ds-recent-comments"><li class="ds-comment ds-show-avatars" data-post-id="6354295387536229122"><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.xevip.cn" onclick="javascript:void(0);"><ds-avatar-image size="32"><img src="https://avatar.duoshuo.com/avatar-50/371/175028.jpg" style="width: 32px; height: 32px;" alt="www.xevip.cn"></ds-avatar-image></a>  </div></ds-avatar><div class="ds-meta"><ds-comment-user-anchor><a rel="nofollow author" target="_blank" href="http://www.xevip.cn">www.xevip.cn</a> </ds-comment-user-anchor><ds-comment-time-text time="2016-11-18T21:11:25+08:00"><span class="ds-time" datetime="2016-11-18T21:11:25+08:00" title="2016年11月18日 下午9:11:25">11月18日</span></ds-comment-time-text></div><div class="ds-thread-title">在 <a href="http://www.liuyunfei.cn/blog/detail/92#comments">sigar的使用入门</a> 中评论</div><div class="ds-excerpt">过来看看你的博客，希望每天都更新一些文章。  诚交友链 站务申请:www.xevip.cn</div></li><li class="ds-comment ds-show-avatars" data-post-id="6353069710073922305"><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://lusongsong.com/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=12920616"><ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/B684B71FE0597B68FBDBE33ED605CD84/100" style="width: 32px; height: 32px;" alt="卢松松博客"></ds-avatar-image></a>  </div></ds-avatar><div class="ds-meta"><ds-comment-user-anchor><a rel="nofollow author" target="_blank" href="http://lusongsong.com/">卢松松博客</a> </ds-comment-user-anchor><ds-comment-time-text time="2016-11-15T13:55:09+08:00"><span class="ds-time" datetime="2016-11-15T13:55:09+08:00" title="2016年11月15日 下午1:55:09">11月15日</span></ds-comment-time-text></div><div class="ds-thread-title">在 <a href="http://www.liuyunfei.cn/blog/detail/173#comments">idea自动生成serialVersionUID</a> 中评论</div><div class="ds-excerpt">完全懵逼</div></li><li class="ds-comment ds-show-avatars" data-post-id="6352646289011770113"><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.liuyunfei.cn/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=12043253"><ds-avatar-image size="32"><img src="http://app.qlogo.cn/mbloghead/dffab81937ad8dbf1296/50" style="width: 32px; height: 32px;" alt="fly"></ds-avatar-image></a>  </div></ds-avatar><div class="ds-meta"><ds-comment-user-anchor><a rel="nofollow author" target="_blank" href="http://www.liuyunfei.cn/">fly</a> </ds-comment-user-anchor><ds-comment-time-text time="2016-11-14T10:32:04+08:00"><span class="ds-time" datetime="2016-11-14T10:32:04+08:00" title="2016年11月14日 上午10:32:04">11月14日</span></ds-comment-time-text></div><div class="ds-thread-title">在 <a href="http://www.liuyunfei.cn/blog/detail/178#comments">lovepanda603先生</a> 中评论</div><div class="ds-excerpt">有其他问题可qq联系。qq在右侧工具条第二个</div></li><li class="ds-comment ds-show-avatars" data-post-id="6352646097055253250"><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.liuyunfei.cn/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=12043253"><ds-avatar-image size="32"><img src="http://app.qlogo.cn/mbloghead/dffab81937ad8dbf1296/50" style="width: 32px; height: 32px;" alt="fly"></ds-avatar-image></a>  </div></ds-avatar><div class="ds-meta"><ds-comment-user-anchor><a rel="nofollow author" target="_blank" href="http://www.liuyunfei.cn/">fly</a> </ds-comment-user-anchor><ds-comment-time-text time="2016-11-14T10:31:19+08:00"><span class="ds-time" datetime="2016-11-14T10:31:19+08:00" title="2016年11月14日 上午10:31:19">11月14日</span></ds-comment-time-text></div><div class="ds-thread-title">在 <a href="http://www.liuyunfei.cn/blog/detail/178#comments">lovepanda603先生</a> 中评论</div><div class="ds-excerpt">是不是servlet版本不对或者没有在eclipse中加入tomcat的servlet包</div></li><li class="ds-comment ds-show-avatars" data-post-id="6352645107589579522"><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.liuyunfei.cn/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=12043253"><ds-avatar-image size="32"><img src="http://app.qlogo.cn/mbloghead/dffab81937ad8dbf1296/50" style="width: 32px; height: 32px;" alt="fly"></ds-avatar-image></a>  </div></ds-avatar><div class="ds-meta"><ds-comment-user-anchor><a rel="nofollow author" target="_blank" href="http://www.liuyunfei.cn/">fly</a> </ds-comment-user-anchor><ds-comment-time-text time="2016-11-14T10:27:29+08:00"><span class="ds-time" datetime="2016-11-14T10:27:29+08:00" title="2016年11月14日 上午10:27:29">11月14日</span></ds-comment-time-text></div><div class="ds-thread-title">在 <a href="http://www.liuyunfei.cn/blog/detail/179#comments">lovepanda603先生</a> 中评论</div><div class="ds-excerpt">可以多图片上传的。shift或ctrl选中都可以的。</div></li></div></div>
				<!-- 多说最新评论 end -->
			</div>
			<div class="am-tab-panel am-fade" id="tab3" style="width: 95%">
				<ul class="ds-recent-visitors" data-num-items="18" style="margin-right: 0px;"><div id="ds-recent-visitors" class="ds-avatar"><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.liuyunfei.cn/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=12043253"><ds-avatar-image size="32"><img src="http://app.qlogo.cn/mbloghead/dffab81937ad8dbf1296/50" style="width: 32px; height: 32px;" alt="fly"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://t.qq.com/li1shun2tao3" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=10144543"><ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/AF2DA58421B5E01402EF1A28C31CD271/100" style="width: 32px; height: 32px;" alt="Allure Love "></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar">  <ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/8D2EF02E75292AD2C6E43AFF8640F143/100" style="width: 32px; height: 32px;" alt="iris_yoyo"></ds-avatar-image></div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.iitboy.com/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=9525996"><ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/416A07309869A434A4668E5AF35832DE/100" style="width: 32px; height: 32px;" alt="IT少年"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.aps.com.cn/apszx.html" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=6223495781534401281"><ds-avatar-image size="32"><img src="http://ds.cdncache.org/avatar-50/281/204253.jpg" style="width: 32px; height: 32px;" alt="山岚云烟"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://dh.iyseo.com/plus/public/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=2338610"><ds-avatar-image size="32"><img src="http://tp1.sinaimg.cn/1878589740/50/40024516962/1" style="width: 32px; height: 32px;" alt="博客导航"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://t.qq.com/haokan688" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=6808395"><ds-avatar-image size="32"><img src="http://app.qlogo.cn/mbloghead/396307f01dc54df06ec2/50" style="width: 32px; height: 32px;" alt="小苏"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://blog.sina.com.cn/s/blog_14c888ba10102w4vd.html" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=10414129"><ds-avatar-image size="32"><img src="http://img3.douban.com/icon/u107881042-2.jpg" style="width: 32px; height: 32px;" alt="小苍男鞋店"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar">  <ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/09E97DEF351ED3E233AA04675E4DB404/100" style="width: 32px; height: 32px;" alt="智验技术"></ds-avatar-image></div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.allink.com.cn/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=6223552724437304065"><ds-avatar-image size="32"><img src="http://ds.cdncache.org/avatar-50/65/204289.jpg" style="width: 32px; height: 32px;" alt="竹语随风"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.mspring.org/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=350214"><ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/FA186C0C24890162A42E72232D607888/100" style="width: 32px; height: 32px;" alt="慕春"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://bing-net.cn/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=13491399"><ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/298D1AE2A8DF7A16A2AFD49ACF90112C/100" style="width: 32px; height: 32px;" alt="BinGe"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://t.qq.com/edisonvip888" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=13863191"><ds-avatar-image size="32"><img src="http://app.qlogo.cn/mbloghead/8eedc925af14bee87b9c/50" style="width: 32px; height: 32px;" alt="痞子啊man"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.helove.net/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=11229276"><ds-avatar-image size="32"><img src="http://tp2.sinaimg.cn/1771781065/50/5735580614/1" style="width: 32px; height: 32px;" alt="黑纳福"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://5mx.net/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=14124643"><ds-avatar-image size="32"><img src="http://ds.cdncache.org/avatar-50/643/201381.jpg" style="width: 32px; height: 32px;" alt="冷夜"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://www.yudouyudou.com/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=753968"><ds-avatar-image size="32"><img src="http://tp4.sinaimg.cn/1960409387/50/5732318190/1" style="width: 32px; height: 32px;" alt="余斗"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://taoxiaozhong.com/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=962813"><ds-avatar-image size="32"><img src="http://ds.cdncache.org/avatar-50/813/201355.jpg" style="width: 32px; height: 32px;" alt="淘小众"></ds-avatar-image></a>  </div></ds-avatar><ds-avatar size="32"><div class="ds-avatar"><a rel="nofollow author" target="_blank" href="http://daliuzi.cn/" onclick="http://lovepanda.duoshuo.com/user-url/?user_id=4465678"><ds-avatar-image size="32"><img src="http://q.qlogo.cn/qqapp/100229475/880186AFB52EBA30D894C0B3B6F24E5C/100" style="width: 32px; height: 32px;" alt="归寒同学"></ds-avatar-image></a>  </div></ds-avatar></div></ul>
			</div>
		</div>
	</div>
</div>

	<!-- 友情链接 -->
	<div class="side-box">
		<div class="my-side-title">
			<span class="am-icon-link"> 友情链接</span>
		</div>
		<div id="youqinglianjie">
			<ul>
				
					<li><a href="http://qiusongsong.net/dh/" target="_blank">博客导航</a></li>
				
					<li><a href="http://bbs.tomoya.cn/" target="_blank">朋也社区</a></li>
				
					<li><a href="http://www.jfinal.com/" target="_blank">JFinal官网</a></li>
				
					<li><a href="http://amazeui.org/" target="_blank">Amaze UI</a></li>
				
					<li><a href="http://layer.layui.com/" target="_blank">Layer</a></li>
				
					<li><a href="http://www.petshow.cc/" target="_blank">宠物秀</a></li>
				
					<li><a href="http://amazeui.github.io/cases/" target="_blank">amazeui案例</a></li>
				
					<li><a href="http://chenzhe.me/" target="_blank">仙桃小白菜</a></li>
				
					<li><a href="http://www.lvoyee.com/" target="_blank">伊成Blog</a></li>
				
					<li><a href="http://ilt.me/" target="_blank">IT技术宅</a></li>
				
					<li><a href="http://luosh.com" target="_blank"> 落事网</a></li>
				
					<li><a href="http://www.qianduan360.com/" target="_blank">前端侠</a></li>
				
					<li><a href="http://www.zhangxiaowu.com/" target="_blank">回忆无香</a></li>
				
				
			</ul>
		</div>
	</div>

  
	<div class="am-sticky-placeholder" style="height: 250px; margin: 0px;"><div data-am-sticky="{animation: 'slide-top'}" style="margin: 0px;">
		<script type="text/javascript">
			/*300*250 创建于 2015-10-31*/
			var cpro_id = "u2378489";
		</script>
		<script src="http://cpro.baidustatic.com/cpro/ui/c.js" type="text/javascript"></script><div id="BAIDU_SSP__wrapper_u2378489_0"><iframe id="iframeu2378489_0" src="http://pos.baidu.com/nchm?rdid=2378489&amp;dc=3&amp;exps=110006&amp;di=u2378489&amp;dri=0&amp;dis=0&amp;dai=1&amp;ps=1703x941&amp;dcb=BAIDU_SSP_define&amp;dtm=HTML_POST&amp;dvi=0.0&amp;dci=-1&amp;dpt=none&amp;tsr=0&amp;tpr=1480856318619&amp;ti=%E6%88%91%E7%9A%84%E5%8D%9A%E5%AE%A2&amp;ari=2&amp;dbv=2&amp;drs=1&amp;pcs=1519x418&amp;pss=1519x1704&amp;cfv=0&amp;cpl=5&amp;chi=25&amp;cce=true&amp;cec=UTF-8&amp;tlm=1480856318&amp;rw=418&amp;ltu=http%3A%2F%2Fwww.liuyunfei.cn%2Fblog%2Fmyblog%2F&amp;ltr=http%3A%2F%2Fwww.liuyunfei.cn%2Fuser%2FpersonalInfo%2F&amp;ecd=1&amp;psr=1536x864&amp;par=1536x824&amp;pis=-1x-1&amp;ccd=24&amp;cja=false&amp;cmi=7&amp;col=zh-CN&amp;cdo=-1&amp;tcn=1480856319&amp;qn=bdcdf442749d25f5&amp;tt=1480856318598.25.26.28" width="300" height="250" align="center,center" vspace="0" hspace="0" marginwidth="0" marginheight="0" scrolling="no" frameborder="0" style="border:0; vertical-align:bottom;margin:0;" allowtransparency="true"></iframe></div>
	</div></div>
 
<!-- 下面是手机部分 -->
<!-- 是手机就取消浮动 -->

<script type="text/javascript">
		   $(function(){
			   $('#demo2').scrollbox({
				    linear: true,
				    step: 1,
				    delay: 0,
				    speed: 100
				  });
		   });
	   </script>
		    </div>
	    	
	  </div>
	  
<div class="am-modal am-modal-confirm" tabindex="-1" id="bolg-delete-confirm">
  <div class="am-modal-dialog">
    <div class="am-modal-hd">警告！</div>
    <div class="am-modal-bd">
      您确定要删除这条记录吗？
    </div>
    <div class="am-modal-footer">
      <span class="am-modal-btn" data-am-modal-cancel="">取消</span>
      <span class="am-modal-btn" data-am-modal-confirm="">确定</span>
    </div>
  </div>
</div>
	
<script type="text/javascript">

 function deleteblog(blogid) {
		      $('#bolg-delete-confirm').modal({
		        relatedTarget: this,
		        onConfirm: function(optons) {
		        	location.href="/blog/delete/"+blogid;
		        },
		        onCancel: function() {
		        }
		      });
		    }
//图片滑动效果
	$(".am_img").on('mouseover', function(){
	    $(this).addClass('bounceIn');
	});
	$('.am_img').on('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
	    $('.am_img').removeClass('bounceIn');
	});
		$("img").lazyload({ effect : 'fadeIn'});
</script>
		

	</div>
</body>
</html>