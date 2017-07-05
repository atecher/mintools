<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>404-页面不见了</title>
  </head>
  <body>
	<div class="container" id="main_content" >
  <div style="width: 100%;height: 400px;background-color:  #fff;" >
  <div style="top: 150px;position: relative;left: 200px;">
  <span>友情提示：您访问的页面可能已经过期，或者路径违法...</span><br/>
  <span><a href="<c:url value="/"/>">我要回到首页...</a></span>
  </ul>
  </div>
  </div>
  </div>
  </body>
</html>
