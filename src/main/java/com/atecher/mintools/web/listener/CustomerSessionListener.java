package com.atecher.mintools.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.atecher.mintools.web.util.CookieUtil;
/**
 * 描述：session监听器，监听session的创建和销毁
 * @作者    mark.han
 * @邮箱    hongwei.han@qq.com
 * @日期    2014-7-29
 * @版本    v1.0
 */
public class CustomerSessionListener implements HttpSessionListener {

	public void sessionCreated(HttpSessionEvent se) {
		se.getSession().setAttribute("session_cookie_code", CookieUtil.createCookieCode(20));
		System.out.println("session Created：" + new java.util.Date());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("session Destroyed：" + new java.util.Date());
	}

	
}
