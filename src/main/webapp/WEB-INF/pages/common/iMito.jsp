<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <script type="text/javascript">var root = "${pageContext.request.contextPath}";</script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css"/>
    <title>在线html格式化、获取文本内容 、压缩-MinTools</title>
    <style type="text/css">
        #iMito{width: 100%;min-height: 500px;}

    </style>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-xs-12">
            <div class="mt-head"><h1>在线美图秀秀</h1></div>
            <div id="iMito" style="height: 600px;"></div>
        </div>

    </div>
</div>
<jsp:include page="../plugins/footer.jsp" flush="true"/>
<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
<script type="text/javascript">
    window.onload=function(){
        /*第1个参数是加载编辑器div容器，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
        xiuxiu.embedSWF("iMito",3,"100%","800");
        //修改为您自己的图片上传接口
        xiuxiu.setUploadURL("http://web.upload.meitu.com/image_upload.php");
        xiuxiu.setUploadType(2);
        xiuxiu.setUploadDataFieldName("upload_file");
        xiuxiu.onInit = function (){
            xiuxiu.loadPhoto("http://open.web.meitu.com/sources/images/1.jpg");
        }
        xiuxiu.onUploadResponse = function (data){
            //alert("上传响应" + data);  可以开启调试
        }
    }
</script>
</body>
</html>