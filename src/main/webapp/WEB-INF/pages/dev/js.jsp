<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../plugins/header.jsp" flush="true"/>
<title>在线JS格式化/净化、加密/解密—MinTools在线工具</title>
<meta name="keywords" content="在线工具,加密,解密,压缩,格式化,净化"/>
<meta name="description" content="为开发设计人员提供在线工具，提供jsbin在线 CSS、JS 调试等其他在线工具"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css"/>
</head>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
	<div class="row">
		<div class="col-xs-8">
			<div class="mt-head"><h1>在线JS格式化/净化、加密/解密</h1></div>
			<div class="form-group">
				<textarea id="content" class="form-control height-large">/*   美化：格式化代码，使之容易阅读	*/
/*   净化：去掉代码中多余的注释、换行、空格等	*/
/*   压缩：将代码压缩为更小体积，便于传输		*/
/*   解压：将压缩后的代码转换为人可以阅读的格式	*/
/*   混淆：将代码的中变量名简短化以减小体积，但可读性差，经混淆后的代码无法还原	*/
/*   如果有用，请别忘了推荐给你的朋友：		*/
/*   javascript在线格式化/净化、加密/解密  */
/*   以下是演示代码*/
var Inote = {};
Inote.JSTool = function(options) {
this.options = options || {};
};
Inote.JSTool.prototype = {
	_name: 'Javascript工具',
_history: {
		'v1.0': ['2014-12-18', 'javascript工具上线'],
		'v1.1': ['2012-12-23', '增加混淆功能'],
		'v1.5':	['2015-01-09', '升级js压缩引擎']
	},
	options: {},
	getName: function() {return this._name;},
	getHistory: function() {
		return this._history;}
};
var jstool = new Inote.JSTool();
	</textarea>

			</div>
			<div class="form-group">
				<%--<div class="btn-group">--%>
				<button class="btn btn-danger" id="btnJSFormat" >格式化(Format)</button>
				<%--<a  class="btn btn-danger dropdown-toggle btn-dropdown"data-toggle="collapse" href="#collapseOne">--%>
					<%--<i class="fa fa-chevron-down" aria-hidden="true"></i>--%>
				<%--</a>--%>
				<%--</div>--%>
				<button class="btn btn-danger" id="btnPurify" >净化(Purify)</button>
				<button class="btn btn-danger" id="btnEncrypt" >加密(Encrypt)</button>
				<button class="btn btn-danger" id="btnDecode" >解密(Decrypt)</button>
			</div>

			<div class="form-group"><textarea id="result" class="form-control height-large" ></textarea></div>
			<jsp:include page="../plugins/comment.jsp"/>
		</div>
		<div class="col-xs-4">
			<jsp:include page="../plugins/sidebar.jsp" flush="true"/>
		</div>
</div>
	</div>

<jsp:include page="../plugins/footer.jsp" flush="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/js/box.js.format.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/js/box.my.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/js/box.packer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/js/box.words.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/thirdparty/js/js.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/mode/javascript/javascript.js"></script>

</body>
</html>
