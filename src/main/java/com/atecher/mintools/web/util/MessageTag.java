package com.atecher.mintools.web.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * 前台系统提示标签
 * @author Robin.han
 */
public class MessageTag extends TagSupport{
	private static final long serialVersionUID = -2682852741433454071L;
	protected String imgpath="/assets/images/icons/";
	public int doStartTag() throws JspException{
		return EVAL_BODY_INCLUDE;
	}
	public int doEndTag() throws JspException{
		Message message=(Message)pageContext.getRequest().getAttribute(Constants.BIZ_MESS);
		if(message==null){
			message=(Message)pageContext.getSession().getAttribute(Constants.BIZ_MESS);
			if(message!=null){
				try {
					pageContext.getOut().write(getOutString(message));
					pageContext.getSession().removeAttribute(Constants.BIZ_MESS);
				} catch (IOException e) {
					
				}
			}
		}else{
			try {
				pageContext.getOut().write(getOutString(message));
				pageContext.getRequest().removeAttribute(Constants.BIZ_MESS);
			} catch (IOException e) {
			}
		}
		return EVAL_PAGE;
	}

	private String getOutString(Message message){
		StringBuilder builder=new StringBuilder();
		int level=message.getLevel();
		String key=null;
		switch(level){
		case 1:
			key="success";
			break;
		case 2:
			key="notice";
			break;
		case 3:
			key="warning";
			break;
		case 4:
			key="error";
			break;
		default:
			key="success";
			break;
		}
		builder.append("<div id=\"message-");
		builder.append(key);
		builder.append("\" class=\"message message-");
		builder.append(key);
		builder.append("\"><div class=\"image\"><img src=\"");
		builder.append(((HttpServletRequest)pageContext.getRequest()).getContextPath());
		builder.append(imgpath);
		builder.append(key);
		builder.append(".png\" alt=\"\" height=\"18\" width=\"18\"/></div>\n<div class=\"text\"><span>");
		builder.append(message.getMessage());
		builder.append("</span></div>\n<div class=\"dismiss\"><a href=\"#message-");
		builder.append(key);
		builder.append("\"><img src=\"");
		builder.append(((HttpServletRequest)pageContext.getRequest()).getContextPath());
		builder.append(imgpath);
		builder.append("cross.png\" alt=\"\" height=\"14\" /></a></div></div>");
		return builder.toString();
	}
	
	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

}
