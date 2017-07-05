<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<header class="mt-header navbar-fixed-top">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <nav class="navbar navbar-inverse" role="navigation">
                    <div class="navbar-header">
                        <a href="${pageContext.request.contextPath}/" class="navbar-brand"><img
                                style="width:165px;height:45px;margin-top:5px;"
                                src="${pageContext.request.contextPath}/themes/default/images/logo.png"/></a>
                    </div>
                    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="${pageContext.request.contextPath}/">全部工具</a></li>
                            <li class="dropdown">
                                <a href="${pageContext.request.contextPath}/category/dev"
                                   class="dropdown-toggle menu-dropdown" data-toggle="dropdown">开发类<strong
                                        class="caret"></strong></a>
                                <ul class="dropdown-menu">
                                    <li><a href="${pageContext.request.contextPath}/category/frontEnd">前端</a></li>
                                    <li><a href="${pageContext.request.contextPath}/category/encrypt">加解密</a></li>
                                    <li><a href="${pageContext.request.contextPath}/category/format">格式化</a></li>
                                </ul>
                            </li>
                            <li><a href="${pageContext.request.contextPath}/category/webmaster">站长类</a></li>
                            <li><a href="${pageContext.request.contextPath}/category/common">通用</a></li>
                            <li><a href="${pageContext.request.contextPath}/markdown/">Markdown</a></li>
                            <li><a href="${pageContext.request.contextPath}/codeblast/">代码爆炸了</a></li>
                            <li><a href="${pageContext.request.contextPath}/doc/">常用文档</a></li>
                        </ul>

                        <%--<ul class="nav navbar-nav navbar-right">--%>
                        <%--<li><a href="#">网站不错，点个赞！</a></li>--%>
                        <%--</ul>--%>
                    </div>

                </nav>
            </div>
        </div>
    </div>
</header>
<header class="mt-search">
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <form class="" action="${pageContext.request.contextPath}/search/" role="search" onsubmit="search.value=searchText.value;return true;">
                    <input name="s" id="search" type="hidden"/>
                    <div class="form-group"><input type="text" class="form-control search clearable" id="searchText"  placeholder="搜索工具、文档，例如：base64、java"> <i class="fa fa-search"></i>
                    </div>
                </form>
            </div>
        </div>
    </div>
</header>