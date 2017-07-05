<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <jsp:include page="../plugins/header.jsp" flush="true"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/json/s.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.css" type="text/css"/>
    <title>JSON在线格式化查看工具-MinTools</title>
<style type="">
    .CodeMirror{height: 600px;  font-size: 13px;line-height: 1.7;font-weight: 500;}
    .mix{padding:0;}
    div.Canvas{border-left-width: 0;}
</style>
<body>
<jsp:include page="../plugins/navigation.jsp" flush="true"/>
<div class="container">
    <div class="mt-head"><h1>JSON在线格式化查看工具</h1></div>
    <div class="row" style="border:1px solid #ccc;padding: 10px;border-bottom-width: 0;">
        <div class="col-xs-6 mix">
            <button id="formatBtn" class="btn btn-success" >格式化</button>
            <button id="compressBtn" class="btn btn-warning" >压缩</button>
            <%--<button type="button" class="btn btn-warning" onclick="SelectAllClicked()">全选</button>--%>
        </div>
        <div class="col-xs-6 mix">
            <button id="viewBtn" class="btn btn-danger" >查看视图</button>
            <span id="TabSizeHolder">缩进量
                          <select id="TabSize" onchange="TabSizeChanged()">
                              <option value="1">1</option>
                              <option value="2" selected="selected">2</option>
                              <option value="3">3</option>
                              <option value="4">4</option>
                              <option value="5">5</option>
                              <option value="6">6</option>
                          </select>
                    </span>

            <label for="QuoteKeys"> <input type="checkbox" id="QuoteKeys" onclick="QuoteKeysClicked()" checked="checked" />  引号</label>&nbsp;
            <label for="CollapsibleView"> <input type="checkbox" id="CollapsibleView" onclick="CollapsibleViewClicked()" checked="checked" /> 显示控制 </label>
            <div class="btn-group" role="group" aria-label="..." id="collapseLevel">
                <button type="button" class="btn btn-danger" level="2">2级</button>
                <button type="button" class="btn btn-danger" level="3">3级</button>
                <button type="button" class="btn btn-danger" level="4">4级</button>
                <button type="button" class="btn btn-danger" level="5">5级</button>
                <button type="button" class="btn btn-danger" level="6">6级</button>
                <button type="button" class="btn btn-danger" level="7">7级</button>
                <button type="button" class="btn btn-danger" level="8">8级</button>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-6 mix">
            <div class="form-group"><textarea id="content" >${requestJson}</textarea></div>
        </div>
        <div class="col-xs-6 mix">
            <div class="form-group"><div id="Canvas" class="Canvas" ></div></div>
       </div>
    </div>
    <jsp:include page="../plugins/comment.jsp"/>
</div>
<jsp:include page="../plugins/footer.jsp" flush="true"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/codemirror/lib/codemirror.js"></script>
<script type="text/javascript" src="<c:url value="/assets/thirdparty/json/json.js"/>"></script>

</body>
</html>