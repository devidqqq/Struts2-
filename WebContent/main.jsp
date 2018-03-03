<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.struts.util.StringUtil"%>
<%@ page import="com.struts.model.User"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" >
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" >
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/My97DatePicker/WdatePicker.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/strutstest.css" >
<title>Insert title here</title>
<%
	User currentUser = (User)session.getAttribute("currentUser");
	if(currentUser==null){
		response.sendRedirect(request.getContextPath()+"/login.jsp");
	}
	String mainPage = (String)request.getAttribute("mainPage");
	if(StringUtil.isEmpty(mainPage)){
		request.setAttribute("mainPage", "common/default.jsp");
	}
%>
</head>
<body>
	<div class="container">
		<jsp:include page="common/head.jsp"></jsp:include>
		<jsp:include page="common/menu.jsp"></jsp:include>
		<jsp:include page="common/navPage.jsp"></jsp:include>
		<jsp:include page="${mainPage }"></jsp:include>
		<jsp:include page="common/foot.jsp"></jsp:include>
	</div>
</body>
</html>