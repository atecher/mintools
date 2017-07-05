<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../plugins/header.jsp" flush="true"/>
<script type="text/javascript">var root="${pageContext.request.contextPath}";</script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css"/>
<title>在线使用MD5算法加解密数据-MinTools</title>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
	<div class="row">
		<div class="col-xs-8">
			<div class="mt-head"><h1>在线使用MD5算法加解密数据</h1></div>
			<div class="form-group"><textarea id="content" ></textarea></div>
			<div class="form-group">
				<button id="btnEncode16" class="btn btn-danger">16位加密(Encrypt16)</button>
				<button id="btnEncode32" class="btn btn-danger">32位加密(Encrypt32)</button>
			</div>
			<div class="form-group"><textarea id="result" ></textarea></div>
			<jsp:include page="../plugins/comment.jsp"/>
		</div>
		<div class="col-xs-4">
			<jsp:include page="../plugins/sidebar.jsp" flush="true"/>
		</div>
	</div>
</div>
<jsp:include page="../plugins/footer.jsp" flush="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/md5/md5.js"></script>
</body>
</html>