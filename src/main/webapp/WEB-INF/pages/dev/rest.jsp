<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <script type="text/javascript">var root = "${pageContext.request.contextPath}";</script>
    <title>RestClient-MinTools</title>
    <style type="text/css">
        .form-inline .input-group .input-group-addon{width: 80px}
        .panel-rest{margin-right: 10px;margin-top:10px;}
        .panel-rest > .panel-heading{background-image: none;}
        textarea.form-control:focus{height: 200px;}
    </style>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-xs-8">
            <div class="mt-head"><h1>RestClient</h1></div>
            <form class="form-inline">
                <div class="input-group col-xs-12 mt-field-rest" >
                    <label for="httpMethod" id="method-label" class="input-group-addon">Method</label>
                    <select class="form-control" id="httpMethod">
                        <option value="GET">GET</option>
                        <option value="POST">POST</option>
                    </select>
                    <label for="domain" class="input-group-addon">URL</label>
                    <input type="text" class="form-control" id="domain" value="www.mintools.net" placeholder="请输入网址：例如mintools.net" style="min-width: 500px;">
                    <span class="input-group-btn">
                        <button class="btn btn-danger" type="button">Send</button>
                            </span>
                    </div>
            </form>

            <div class="panel panel-default panel-rest">
                <div class="panel-heading">
                    Request Body
                </div>
                <div class="panel-body">
                    <textarea class="form-control"></textarea>
                </div>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/extlink/extlink.js"></script>
</body>
</html>