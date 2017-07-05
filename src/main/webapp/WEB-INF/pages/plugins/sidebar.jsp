<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="panel panel-default panel-custom">
	<div class="panel-heading">快捷操作</div>
	<div class="panel-body">
		<button type="button" class="btn btn-link" onclick="AddFavorite(window.location,document.title)">+ 添加收藏</button>
		<button type="button" class="btn btn-link" onclick="setHome(this,window.location)">+ 设为首页</button>
	</div>
</div>

<p>
	<iframe width="100%" height="550" class="share_self" frameborder="0" scrolling="no"
			src="http://widget.weibo.com/weiboshow/index.php?language=&width=0&height=550&fansRow=1&ptype=1&speed=0&skin=5&isTitle=1&noborder=1&isWeibo=1&isFans=1&uid=5123461973&verifier=5987516b&dpc=1"></iframe>
</p>