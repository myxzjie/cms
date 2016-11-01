<%@page import="com.xzjie.gypt.system.web.WebUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="language" value="<%=WebUtils.getLanguage(request)%>"/>
<c:set var="curl" value="<%=request.getContextPath() %>" />
<c:set var="ctx" value="<%=WebUtils.getBasePath(request)%>"/>

<c:set var="frontPath" value="${ctx}/f"/>
<c:set var="ctx_front" value="${ctx}/f"/>

<c:set var="site"  value="<%=WebUtils.getSite(request)%>"  />
<c:set var="uploadImageWeb" value="<%=WebUtils.getUploadImageWeb()%>"/>
  
