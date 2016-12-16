<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/tag.jsp"%>
<!doctype html>
<html>
<head>
  <%@ include file="include/meta.jsp" %>
  <title>修改个人信息-${site.siteName}</title>
  <%-- <meta name="description" content="${model.keywords}">
  <meta name="keywords" content="${model.description}"> --%>
  <%@ include file="include/style.jsp" %>
  <link rel="stylesheet" href="${ctx}/resources/assets/lib/Jcrop/css/jquery.Jcrop.css" type="text/css">
</head>
<body id="blog">

<%@include file="include/navs.jsp" %>


<!-- content srart -->
<div class="am-g am-g-fixed blog-fixed">
	<ol class="am-breadcrumb" style=" margin-bottom: 0rem;">
			  <li><a href="/" class="am-icon-home">首页</a></li>
			  <li><a href="/user/personalInfo">个人主页</a></li>
			  <li class="am-active">详情</li>
			</ol>
	<hr style="margin: 0 0 0.5rem;">	
	
	<div class="am-u-md-8 am-u-sm-12" style="padding-left:0">
    	<div data-am-widget="tabs" class="am-tabs am-tabs-default am-no-layout">
			  <ul class="am-tabs-nav am-cf">
			   <li class="am-active">
			      <a href="[data-tab-panel-0]">我的资料</a>
			    </li>
			    <li class="">
			      <a href="[data-tab-panel-1]">资料修改</a>
			    </li>
			    <li class="">
			      <a href="[data-tab-panel-2]" onclick="editavatar();">头像修改</a>
			    </li>
			    <li class="">
			      <a href="[data-tab-panel-3]">密码修改</a>
			    </li>
			  </ul>
			  <div class="am-tabs-bd" style="touch-action: pan-y; user-select: none; -webkit-user-drag: none; -webkit-tap-highlight-color: rgba(0, 0, 0, 0);">
			  <div data-tab-panel-0="" class="am-tab-panel am-active am-in">
			  		<table class="am-table am-table-bordered am-table-striped am-table-hover">
			  			<thead>
			  				<tr>
			  					<th width="20%">类别</th>
			  					<th width="80%">内容</th>
			  				</tr>
			  			</thead>
					    <tbody>
					        <tr>
					            <td>用户名</td>
					            <td>${model.name}</td>
					        </tr>
					        <tr>
					            <td>昵称</td>
					            <td>${model.nickName}</td>
					        </tr>
					        <tr>
					            <td>性别</td>
					            <td>
					             <c:choose>
					             <c:when test="${model.sex==0}">男</c:when>
					             <c:when test="${model.sex==1}">女</c:when>
					             <c:otherwise>未知</c:otherwise>
					             </c:choose>
					            </td>
					        </tr>
					        <tr>
					        	<td>年龄</td>
					        	<td>${age}</td>
					        </tr>
					        <tr>
					            <td>民族</td>
					            <td></td>
					        </tr>
					        <tr>
					            <td>邮箱</td>
					            <td>${model.eMail}</td>
					        </tr>
					        <tr>
					            <td>手机</td>
					            <td>${model.phone}</td>
					        </tr>
					        <tr>
					            <td>QQ</td>
					            <td>${model.qq}</td>
					        </tr>
					        <tr>
					            <td>地址</td>
					            <td>${model.address}</td>
					        </tr>
					        <tr>
					            <td>职业和工作</td>
					            <td>${model.job}</td>
					        </tr>
					        <tr>
					            <td>创建时间</td>
					            <td><fmt:formatDate value="${model.createDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
					        </tr>
					        <tr>
					            <td>最后登陆时间</td>
					            <td><fmt:formatDate value="${model.loginDate}" pattern="yyyy-MM-dd hh:mm:ss" /></td>
					        </tr>
					        <!-- <tr>
					            <td>类型</td>
						            <td>注册用户</td>
					        </tr> -->
					    </tbody>
					</table>
			  </div>
			  	<!-- 资料修改tab -->
			    <div data-tab-panel-1="" class="am-tab-panel">
			    	<form id="persionalInfo_form" class="am-form" action="${ctx_front}/personal/update" method="post"  novalidate="novalidate">
					  <fieldset>
					   	<div class="am-form-group">
					      <label for="name">用户名 <span style="color: red;">*</span></label>
					      <input type="hidden" name="userId" value="${model.userId}">
					      <input type="text" id="name" name="name" value="${model.name}" pattern="^[a-z-A-Z]{1}([a-z-A-Z0-9_]){1,19}$" placeholder="请输入用户名（3~15位数字字母下划线)" required>
					    </div>
					    <div class="am-form-group">
					      <label for="nickName">昵称 </label>
					      <input type="text" id="nickName" name="nickName" value="${model.nickName}" maxlength="6" placeholder="请输入呢称（<=6位任意字符)" required>
					    </div>
					    <div class="am-form-group">
					      <label for="persionalInfo-sex">性别</label>
					      <select id="persionalInfo-sex" name="ex">
						        <option <c:if test="${model.sex==0}">selected="selected"</c:if> value="0">男</option>
						        <option <c:if test="${model.sex==1}">selected="selected"</c:if> value="1">女</option>
					      </select>
					      <span class="am-form-caret"></span>
					    </div>
					    
					    <div class="am-form-group">
					      <label for="eMail">邮件</label>
					      <input type="email" id="eMail" name="eMail" value="${model.eMail}"  placeholder="请输入电子邮件" required pattern="^((([a-zA-Z]|\d|[!#\$%&amp;'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-zA-Z]|\d|[!#\$%&amp;'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-zA-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-zA-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-zA-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-zA-Z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-zA-Z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-zA-Z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$">
					    </div>
					    <div class="am-form-group">
					    	<label for="persionalInfo-mobile">手机</label>
					    	<input type="number" name="phone" value="${model.phone}" id="phone" required maxlength="11" placeholder="请输入手机" pattern="^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$">
					    </div>
					    
					    <div class="am-form-group">
					    	<label for="birtn">"出生日期"</label>
					    	<input id="birtn" name="birtn" type="text" class="am-form-field" value="<fmt:formatDate value="${model.birtn}" pattern="yyyy-MM-dd" />" placeholder="出生日期" data-am-datepicker="{format: 'yyyy-mm-dd'}" readonly />
					    </div>
					    <div class="am-form-group">
					      <label for="nation">民族(如：汉)</label>
					      <input type="text" id="nation" name="nation" value="${model.nation}" placeholder="请输入民族全称">
					    </div>
					    
					     <div class="am-form-group">
					    	<label for="qq">QQ</label>
					    	<input type="number" name="qq" value="${model.qq}" id="qq" maxlength="20" placeholder="请输入QQ" pattern="^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$">
					    </div>
					    <div class="am-form-group">
					    	<label for="address">地址</label>
					    	<input type="text" name="address" value="${model.address}" id="address" placeholder="请输入地址">
					    </div>
					  	<div class="am-form-group">
					      <label for="persionalInfo-job">职业和工作</label>
					      <input type="text" id="persionalInfo-job" name="job" value="${model.job}" maxlength="10" placeholder="请输入职业和工作)">
					    </div>
					    <p><button type="submit" class="am-btn am-btn-primary">提交</button></p>
					  </fieldset>
					</form>
			    </div>
			    <!-- 头像修改pannel -->
			    <div id="personalInfo-editavatardiv" data-tab-panel-2="" class="am-tab-panel am-active am-in" style="height: 800px;">

					<div class="am-container">
						<form id="personalInfo-getImage" action="/user/getImage">
							<div class="am-form-group am-form-file">
							  <button type="button" class="am-btn am-btn-danger am-btn-sm">
							    <i class="am-icon-cloud-upload"></i> 选择要上传头像</button>
							  <input type="file" name="file" accept="image/*" multiple="multiple" onchange="cahngavatar();">
							  </div>
						</form>
					 	<form id="FaceUpload" name="FaceUpload" method="post" action="/user/updateAvatar" style="padding-top: 10px">
					 		<input type="hidden" name="avatar" id="avatarname" value="32707921ac424e8780d69ef9f5267295.jpg">
					        <input type="hidden" id="x1" name="x1" value="0">
					        <input type="hidden" id="y1" name="y1" value="0">
					        <input type="hidden" id="cw" name="cw" value="100">
					        <input type="hidden" id="ch" name="ch" value="100">
						  	<button type="submit" class="am-btn am-btn-secondary  am-btn-lg"> <i class="am-icon-check"></i> 提交</button><span style="color: red;">请确定下面的头像调整合适后再提交</span>
					    </form>
					    <hr>
						
						<div>
						<c:if test="${model.headPortrait==null}">
						<img id="target" alt="user default" src="${ctx}/resources/front/images/user_default.jpg" />
						</c:if>
						<c:if test="${model.headPortrait!=null}">
						<img alt="${model.name}" id="target" src="${uploadImageWeb}${model.headPortrait}" >
						</c:if>
						</div>
					</div>
				</div>
			    <!-- 密码修改pannel -->
			    <div data-tab-panel-3="" class="am-tab-panel">
			    	<div class="am-g">
					  <div class="am-u-md-8 am-u-sm-centered">
					    <form class="am-form" enctype="multipart/form-data" method="post" action="/user/updatePassword">
					      <fieldset class="am-form-set">
					      	
						        <input type="password" name="oldpwd" placeholder="原密码">
					      	
					        <input type="password" name="newpwd" placeholder="新密码">
					        <input type="password" name="newpwd_ag" placeholder="重复密码">
					      </fieldset>
					      <button type="submit" class="am-btn am-btn-primary am-btn-block">提交</button>
					    </form>
					  </div>
					</div>
			    </div>
			  </div>
			</div>
    </div>

    <div class="am-u-md-4 am-u-sm-12 ">
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor">
    	<a href="${ctx_front}/blog/edit?cid=${site.siteId}" class="am-btn am-btn-primary am-btn-block">发布博文</a>
    	</div>
    	
    	<div data-am-widget="intro" class="am-intro am-cf am-intro-default am-no-layout blog-bor" style="margin-top: 5px;">
    	<h2 style="border-bottom: 1px #eee solid; margin: 0 0 0.5rem; padding-left: 0.5rem">关于博主</h2>
    	<div class="blog-sidebar-widget">
    	<img style="width: 100%" alt="user default" src="${ctx}/resources/front/images/user_default.jpg" />
    	<div style="text-align: left;">
    		<ul class="am-list am-list-static am-list-border">
			  <li>
			    <i class="am-icon-user-secret"></i>
			   	 博主:
			   	 <shiro:principal property="username"/>
			   	 <button class="am-btn am-badge-warning am-btn-xs am-fr" data-am-popover="{content: '编辑个人信息', trigger: 'hover focus'}" onclick="javascript:location.href='/personal/edit'">编辑</button>
			  </li>
			  <li>
			    <i class="am-icon-mortar-board"></i>
			   	职业:java工程师
			  </li>
			  <li>
			    <i class="am-icon-newspaper-o"></i>
			   	 简介
			   	 
			  </li>
			 </ul>
    	</div>
    	</div>
    	</div>
    	<div data-am-widget="list_news" class="am-list-news am-list-news-default " >
  			<!--列表标题-->
    		<div class="am-list-news-hd am-cf">
	       		<!--带更多链接-->
	        	<!-- <a href="##" class=""> -->
	          	<h2>内容推荐</h2>
	            <!-- <span class="am-list-news-more am-fr">更多 &raquo;</span> -->
	        	<!-- </a> -->
          	</div>

  			<div class="am-list-news-bd">
			  <ul class="am-list recommend-list">
			
			  </ul>
  			</div>

    	</div>
    
    	<%@include file="include/about.jsp" %>
        
    </div>
</div>
<!-- content end -->

<%@include file="include/footer.jsp" %>

<%@include file="include/script.jsp" %>
<script type="text/javascript" src="${ctx}/resources/assets/lib/jquery-form/jquery-form.js"></script>
<script type="text/javascript" src="${ctx}/resources/assets/lib/Jcrop/js/jquery.Jcrop.js"></script>

<script type="text/javascript">

var rooturl='';
var jcrop_api;

$(function(){
	toolObj.submit();
	
	$('#target').Jcrop({
    	bgColor: 'black',
        bgOpacity: 0.4,
        setSelect: [0, 0, 230,230],  //设定4个角的初始位置
        aspectRatio: 1 / 1,
        onChange: showCoords,   //当裁剪框变动时执行的函数
        onSelect: showCoords,
        minSize:[100,100],
        maxSize:[230,230],
    },function(){
      jcrop_api = this;
    });
});

var toolObj={
		submit:function(){
			this.edit();
		},
		edit:function(){
			var $form =$("#persionalInfo_form");
			$form.validator({
		          submit: function() {
					debugger
		            var formValidity = this.isFormValid();
		            if (!formValidity){
		            	return false;
		            }

		            var data={};
		            data=$form.serializeJSON();
		            $.ajax({
						type: "POST",
						data: data,
						dataType: 'json',
						url:$form[0].action,
						success: function(res){
							if(res.success){
								location.reload();
							}else{
								layer.alert(res.message, {icon: 2});
							}
						}
					});

		            return false;
		          }
		        });
		}
}

function cahngavatar(){
	  $("#personalInfo-getImage").ajaxSubmit({
		  url:global.basePath+'/upload/image?dir=head_portrait',
		  type:'post',
		  dataType:'json',
		  success:function(res) {   
			 
         	var wi, he;
           if(res.success){
        	  var data=res.data;
        	  debugger
        	  getImageWidth(rooturl+"/attached/temp/"+data.uriPath,function(w,h){
        		  if(w!=undefined){
	            		wi=w;
          		}
          		if(h!=undefined){
	            		he=h;
          		}
          		if(w<100||h<100||h>800){
          			layer.alert('图片尺寸不能小于100*100px且图片高度不能大于800', {
          			    skin: 'layui-layer-moilv' //样式类名
          			    ,closeBtn: 0
          			    ,icon:0
          			});
          			 return false;
          		}
        	  });
        	  
        	  if(wi<100||he<100||he>800){
        			layer.alert('图片尺寸不能小于100*100px且图片高度不能大于800', {
        			    skin: 'layui-layer-moilv' //样式类名
        			    ,closeBtn: 0
        			    ,icon:0
        			});
        		}else{
		            	 $("#target").attr("src", rooturl+"/attached/temp/"+data.url);
		            	$("#avatarname").val(data.url);
		             	jcrop_api.setImage(rooturl+"/attached/temp/"+data.url);
		            	 $('#target').Jcrop({
		                    bgColor: 'black',
		                    bgOpacity: 0.4,
		                    setSelect: [0, 0, 100,100],  //设定4个角的初始位置
		                    aspectRatio: 1 / 1,
		                    onChange: showCoords,   //当裁剪框变动时执行的函数
		                    onSelect: showCoords,   //当选择完成时执行的函数
		                    minSize:[100,100],
		                    maxSize:[300,300],
		                },function(){
		                    jcrop_api = this;
		                });
		            	 $(".jcrop-holder").attr("style","width: "+wi+"px; height: "+he+"px; position: relative; background-color: black;");
		            	 $(".jcrop-holder").children("img").attr("style","width:"+wi+"px;height:"+he+"px;");
		            	 $(".jcrop-holder").children(".jcrop-tracker").attr("style","width: "+wi+"px; height: "+he+"px; position: absolute; top: -2px; left: -2px; z-index: 290; cursor: crosshair;");
        		}
        	  
          }else{
        	  layer.alert(res.message, {icon: 2});
          }
        } 
		  
	  });
	  
}


</script>
<script type="text/javascript">
  
//当裁剪框变动时，将左上角相对图片的X坐标与Y坐标，宽度以及高度放到<input type="hidden">中(上传到服务器上裁剪会用到)
  function showCoords(c) {
      $("#p1").text(c.x + "   " + c.y + "   " + c.w + "   " + c.h );
      $("#x1").val(c.x);
      $("#y1").val(c.y);
      $("#cw").val(c.w);
      $("#ch").val(c.h);

  }

  function getImageWidth(url,callback){
  	var img = new Image();
  	img.src = url;
  	
  	// 如果图片被缓存，则直接返回缓存数据
  	if(img.complete){
  	    callback(img.width, img.height);
  	}else{
              // 完全加载完毕的事件
  	    img.onload = function(){
  		callback(img.width, img.height);
  	    }
          }
  	
  }
  function setDefault(){
	  console.info('.........');
	  $("#target").attr("src", rooturl+"/attached/avatar/default.jpg");
  	$("#avatarname").val("default");
  	 $('#target').Jcrop({
          bgColor: 'black',
          bgOpacity: 0.4,
          setSelect: [0, 0, 100,100],  //设定4个角的初始位置
          aspectRatio: 1 / 1,
          onChange: showCoords,   //当裁剪框变动时执行的函数
          onSelect: showCoords,   //当选择完成时执行的函数
          minSize:[100,100],
          maxSize:[300,300],
      },function(){
          jcrop_api = this;
      });
   	jcrop_api.setImage(rooturl+"/attached/avatar/default.jpg");
  }
</script>
</body>
</html>