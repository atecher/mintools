<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <script type="text/javascript">var root = "${pageContext.request.contextPath}";</script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css"/>
    <title>在线html格式化、获取文本内容 、压缩-MinTools</title>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-xs-8">
            <div class="mt-head"><h1>在线html格式化、获取文本内容 、压缩</h1></div>
            <div class="form-group">
			<textarea id="content">
<!DOCTYPE html><html>
<head>
    <meta charset="utf-8"/>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
<div id="wrap">
    <div id="header"><h1>html在线工具</h1>
        <!--   如果有用，请别忘了推荐给你的朋友：		-->
        <!--   Html在线格式化-->
    </div>
    <div id="main">
        <p>
        <dl>
            <dd>2015-11-14 修复格式化功能，增加压缩</dd>
            <dd>2016-06-05 修改 html 压缩引擎</dd>
        </dl>
        </p>
    </div>
    <div id="footer">This is just an example.</div>
</div>
</body>
</html></textarea>
            </div>
            <div class="form-group">
                <button class="btn btn-danger" id="btnHtmlFormat">格式化(Beutify)</button>
                <button class="btn btn-danger" id="btnHtmlClear">获取文本(Text)</button>
                <button class="btn btn-danger" id="btnHtmlYS">压缩(Pack)</button>
            </div>
            <div class="form-group"><textarea id="result"></textarea></div>
            <jsp:include page="../plugins/comment.jsp"/>
        </div>
        <div class="col-xs-4">
            <jsp:include page="../plugins/sidebar.jsp" flush="true"/>
        </div>
    </div>
</div>
<jsp:include page="../plugins/footer.jsp" flush="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/javascript/javascript.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/xml/xml.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/css/css.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/html/html.js"></script>
</body>
</html>