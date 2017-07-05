package com.atecher.mintools.web.controller;

import com.atecher.mintools.web.generic.GenericActionController;
import com.atecher.mintools.web.util.WebForwardConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommonController extends GenericActionController{
	@RequestMapping(value = "/meitu",method = RequestMethod.GET)
	public String js() {
			return WebForwardConstants.COMMON_IMITO;
	}

}
