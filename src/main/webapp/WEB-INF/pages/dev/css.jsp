<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css"/>
    <title>在线CSS格式化/净化 、压缩-MinTools</title>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
    <div class="row">
        <div class="col-xs-8">
            <div class="mt-head"><h1>在线CSS格式化/净化 、压缩</h1></div>
            <div class="form-group">
                <textarea id="content" class="content">/*   美化：格式化代码，使之容易阅读			*/
/*   净化：将代码单行化，并去除注释   */
/*   整理：按照一定的顺序，重新排列css的属性   */
/*   压缩：将代码最小化，加快加载速度   */

/*   如果有用，请别忘了推荐给你的朋友：		*/
/*   在线CSS格式化/净化 、压缩  */
/*   v1.1 2014-12-11   */

/*   以下是演示代码				*/

body, div, dl, dt, dd, ul, ol, li,
h1, h2, h3, h4, h5, h6, pre, code,
form, fieldset, legend, input, button,
textarea, p, blockquote, th, td {
    margin: 0;
    padding: 0;
}
fieldset, img {
    border: 0;
}
/* remember to define focus styles! */
:focus {
    outline: 0;
}
address, caption, cite, code, dfn,
em, strong, th, var, optgroup {
    font-weight: normal;
    font-style: normal;
}
 
h1, h2, h3, h4, h5, h6 {
    font-weight: normal;
    font-size: 100%;
}
abbr, acronym {
    border: 0;
    font-variant: normal;
}
 
input, button, textarea,
select, optgroup, option {
    font-weight: inherit;
    font-style: inherit;
    font-size: inherit;
    font-family: inherit;
}
code, kbd, samp, tt {
    font-size: 100%;
}
/*@purpose To enable resizing for IE */
/*@branch For IE6-Win, IE7-Win */
input, button, textarea, select {
    *font-size: 100%;
}
body {
    line-height: 1.5;
}
ol, ul {
    list-style: none;
}
/* tables still need 'cellspacing="0"' in the markup */
table {
    border-collapse: collapse;
    border-spacing: 0;
}
caption, th {
    text-align: left;
}
sup, sub {
    vertical-align: baseline;
    font-size: 100%;
}
/* remember to highlight anchors and inserts somehow! */
:link, :visited , ins {
    text-decoration: none;
}
blockquote, q {
    quotes: none;
}
blockquote:before, blockquote:after,
q:before, q:after {
    content: '';
    content: none;
}</textarea>
            </div>
            <div class="form-group">
                <button class="btn btn-danger" id="btnCSSFormat">格式化(Format)</button>
                <button class="btn btn-danger" id="btnCSSClear">净化(Purify)</button>
                <button class="btn btn-danger" id="btnCSSYS">压缩(Pack)</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/css/css.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/css/yahoo.compressor.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/css/css.js"></script>

</body>
</html>