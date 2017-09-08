package com.atecher.mintools.web.controller;

import com.aliyuncs.domain.model.v20160511.GetWhoisInfoResponse;
import com.atecher.mintools.model.Page;
import com.atecher.mintools.service.IWebsiteService;
import com.atecher.mintools.util.WhoisUtils;
import com.atecher.mintools.web.util.ResponseResult;
import com.atecher.mintools.web.util.WebForwardConstants;
import com.atecher.mintools.web.util.extlink.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class WebMasterController {

	@Autowired
	private IWebsiteService websiteService;

	@RequestMapping(value="/whois",method = RequestMethod.GET)
	public String whois() {
			return WebForwardConstants.WEBMASTER_WHOIS;
	}
	@RequestMapping(value="/whois/search",method = RequestMethod.POST)
	@ResponseBody
	public GetWhoisInfoResponse whois(@RequestParam("domainName") String domainName) throws Exception {
		GetWhoisInfoResponse response= WhoisUtils.getWhoisInfo(domainName);
		return response;
	}

	@RequestMapping(value="/extlink",method = RequestMethod.GET)
	public String extlink() {
		return WebForwardConstants.WEBMASTER_EXTLINK;
	}

	@RequestMapping(value="/extlink",method = RequestMethod.POST)
	@ResponseBody
	public ResponseResult getData(@RequestParam(value = "domain",defaultValue = "www.mintools.net") String domain, @RequestParam(value="page",defaultValue = "1") Integer page, @RequestParam(value="size",defaultValue = "20") Integer size) throws Exception {
		Page<String> extLinks= websiteService.queryExtlinkForPage(page,size,new HashMap<String, Object>());
		List<String> datas=extLinks.getRows();
		List<String> result= new ArrayList<>();
		for(String data:datas){
			result.add(MessageFormat.format(data,domain));
		}
		extLinks.setRows(result);

		PageResult pageResult=new PageResult();
		pageResult.setRows(result);
		pageResult.setTotal(extLinks.getTotal());
		pageResult.setLimit(size);
		pageResult.setPage(page);
		return new ResponseResult("success",pageResult);
	}




	
}
