package com.atecher.mintools.web.controller;

import com.atecher.mintools.web.util.WebForwardConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommonController {
	@RequestMapping(value = "/meitu",method = RequestMethod.GET)
	public String js() {
			return WebForwardConstants.COMMON_IMITO;
	}

}
