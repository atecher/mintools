<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <script type="text/javascript">var root = "${pageContext.request.contextPath}";</script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css"/>
    <title>Whois查询-MinTools</title>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-xs-8">
            <div class="mt-head"><h1>Whois查询</h1></div>
            <div class="form-group">
                <div class="input-group mt-form-search">
                    <input type="text" class="form-control search" id="whois_search" placeholder="请输入网址：例如mintools.net">
                      <span class="input-group-btn">
                        <button class="btn btn-danger" type="button" id="searchBtn">查询</button>
                      </span>
                </div>
            </div>
            <div class="form-group mt-result" id="result">

            </div>
            <jsp:include page="../plugins/comment.jsp"/>
        </div>
        <div class="col-xs-4">
            <jsp:include page="../plugins/sidebar.jsp" flush="true"/>
        </div>
    </div>
</div>
<jsp:include page="../plugins/footer.jsp" flush="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/whois/whois.js"></script>
</body>
</html>