<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <script type="text/javascript">var root = "${pageContext.request.contextPath}";</script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/thirdparty/codeblast/monokai.css"/>
    <title>代码爆炸了</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/thirdparty/codeblast/closetag.js"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/javascript/javascript.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/xml/xml.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/css/css.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/htmlmixed/htmlmixed.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/codeblast/code-blast.js"></script>
    <style type="text/css">
        body{background: #272822;}
        body *{font-size: 20px;}
        .CodeMirror{height: 100%;border-width: 0;}
    </style>
</head>
<body>
<script>
    window.cm = CodeMirror(document.body, {
        lineNumbers: true,
        mode:  "htmlmixed",
        theme: 'monokai',
        lineWrapping: true,
        autofocus: true,
        width: '100%',
        height: '100%',
        tabSize: 2,
        value: "<article>\n  <h1>Code blast plugin for Codmirror</h1>\n  <div class=\"based-on\">\n    Based on Joel Besada's experiment:\n    https://twitter.com/JoelBesada/status/670343885655293952\n  </div>\n  \n  <div>\n\t  Github: https://github.com/chinchang/code-blast-codemirror/\n  </div>\n  \n</article>",
        autoCloseTags: true,
        blastCode: { effect: 2 },
    });
    cm.on("change", function (instance, change) {
        if (window.ev) { return; }
        window.ev = 1;
        window._gaq && _gaq.push(['_trackEvent', 'code-blast', 'used']);
    });
</script>
<script type="text/javascript">
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?af8abc7e6c116587b3b7bf7e208b891e";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s);
    })();
</script>
</body>
</html>