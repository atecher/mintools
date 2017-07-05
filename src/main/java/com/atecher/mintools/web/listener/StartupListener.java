package com.atecher.mintools.web.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 描述：系统启动监听，用户参数初始化
 * @作者    mark.han
 * @邮箱    hongwei.han@qq.com
 * @日期    2014-7-29
 * @版本    v1.0
 */
public class StartupListener  implements ServletContextListener {
    private  final Log log = LogFactory.getLog(StartupListener.class);
	public void contextInitialized(ServletContextEvent event) {
		log.debug("Initializing context...");

	}
	public void contextDestroyed(ServletContextEvent sce) {
		
	}

}
