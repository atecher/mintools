<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta name="keywords" content="在线工具,加密/解密，Markdown,less,node.js api,QR Code"/>
	<meta name="description" content="为开发设计人员提供在线工具，提供jsbin在线 CSS、JS 调试等其他在线工具"/>
	<jsp:include page="plugins/header.jsp" flush="true"/>
	<title>MinTools在线工具-开发者的在线工具箱</title>
</head>
<body>
<jsp:include page="plugins/navigation.jsp" flush="true"/>
<div class="container">
	<div class="row">
		<div class="col-xs-8">
			<div class="list-group mt-packages">
				<c:forEach items="${tools}" var="t">
					<c:set var="link" scope="request" value="/"/>
					<a class="mt-package list-group-item" href="${t.relative==1?pageContext.request.contextPath:""}${t.href}" target="${t.target}">
						<div class="row" id="tool-${t.code}">
							<div class="col-xs-2"><img src="${pageContext.request.contextPath}/themes/default/images/tools/${t.icon}" alt="${t.title}" /></div>
							<div class="col-xs-2">${t.title}</div>
							<div class="col-xs-8">
								<p>${t.description}</p>
								<p>标签：${t.tag}</p>
							</div>
						</div>
					</a>
				</c:forEach>
			</div>
		</div>
		<div class="col-xs-4">
			<jsp:include page="plugins/sidebar.jsp" flush="true"/>

		</div>
	</div>
</div>
<jsp:include page="plugins/footer.jsp" flush="true"/>
</body>
</html>