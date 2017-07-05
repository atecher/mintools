package com.atecher.mintools.web.generic;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import com.atecher.mintools.web.util.CookieUtil;

/**
 * 描述：控制层基类
 * @作者    mark.han
 * @邮箱    hongwei.han@qq.com
 * @日期    2014-7-29
 * @版本    v1.0
 */
@Controller
public class GenericActionController {
	private static final Logger logger = LoggerFactory.getLogger(GenericActionController.class);
	
	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";
	
	/**
	 * 描述：向客户端回写数据
	 * @作者 mark.han
	 * @日期 2014-7-29
	 * @邮箱 hongwei.han@qq.com
	 * @param response
	 * @param data
	 */
	protected void writeToClient(HttpServletResponse response,String data){
		try {
			writeToClient(response, data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 描述：向客户端回写数据
	 * @作者 mark.han
	 * @日期 2014-7-29
	 * @邮箱 hongwei.han@qq.com
	 * @param response
	 * @param data
	 */
	protected void writeToClient(HttpServletResponse response,byte[] data){
		response.setContentType(CONTENT_TYPE);  
		OutputStream os=null;
		try {
			os=response.getOutputStream();
			os.write(data);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(os!=null){
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	/**
	 * 描述：获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
	 * @作者 mark.han
	 * @日期 2014-7-29
	 * @邮箱 hongwei.han@qq.com
	 * @param request
	 * @return
	 */
	protected String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (!StringUtils.isBlank(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }
	/**
	 * 描述：获取访问者信息
	 * @作者 mark.han
	 * @日期 2014-7-29
	 * @邮箱 hongwei.han@qq.com
	 * @param request
	 * @param response
	 * @return
	 */
	protected Map<String,Object> getVisitorInfo(HttpServletRequest request,HttpServletResponse response) {
        String real_ip = request.getHeader("X-Real-IP");
        String agent_ip=request.getRemoteAddr();
        if (StringUtils.isBlank(real_ip) ||"unknown".equalsIgnoreCase(real_ip)) {
        	real_ip=request.getHeader("X-Forwarded-For");
        }
        if (!StringUtils.isBlank(real_ip) && !"unknown".equalsIgnoreCase(real_ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = real_ip.indexOf(',');
            if (index != -1) {
            	real_ip=real_ip.substring(0, index);
            } 
        }
        
        if(StringUtils.isBlank(real_ip) || "unknown".equalsIgnoreCase(real_ip)) {    
        	real_ip = request.getHeader("WL-Proxy-Client-IP");    
         } 

        if(StringUtils.isBlank(real_ip) || "unknown".equalsIgnoreCase(real_ip)) {    
        	real_ip=agent_ip;
         }
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("real_ip", real_ip);
        map.put("agent_ip", agent_ip);
        Cookie cookie=  CookieUtil.getCookie(request, "yd_cookie_code");
        String cookie_code="";
        if(cookie==null){
        	cookie_code=(String)request.getSession().getAttribute("session_cookie_code");
        	CookieUtil.setCookie(request, response, "yd_cookie_code", cookie_code);
        	logger.debug("生成了用户的cookie_code:{}",cookie_code);
        }else{
        	cookie_code=cookie.getValue();
        }
        map.put("cookie_code", cookie_code);
        return map;
    }
	
	
	
}
