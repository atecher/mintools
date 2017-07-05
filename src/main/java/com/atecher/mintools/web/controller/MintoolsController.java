package com.atecher.mintools.web.controller;

import com.atecher.mintools.service.IGenericService;
import com.atecher.mintools.web.generic.GenericActionController;
import com.atecher.mintools.web.util.WebForwardConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MintoolsController extends GenericActionController {

	@Autowired
	private IGenericService genericService;

	@RequestMapping(value = "/index",method = RequestMethod.GET)
	public String index(@RequestParam(value="s",required = false) String search, Model model) {
		List<Map> tools=genericService.selectList("com.atecher.tools.mapper.ToolMapper.findToolAll",null);
		model.addAttribute("tools",tools);
		return WebForwardConstants.INDEX;
	}
	
	@RequestMapping(value = "/category/{code}",method = RequestMethod.GET)
	public String category(@PathVariable("code") String code,Model model) {
		List<Map> tools=genericService.selectList("com.atecher.tools.mapper.ToolMapper.findToolsByCategory",code);
		model.addAttribute("tools",tools);
		return WebForwardConstants.INDEX;
	}

}
