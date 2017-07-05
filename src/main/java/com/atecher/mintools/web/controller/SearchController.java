package com.atecher.mintools.web.controller;

import com.atecher.mintools.service.IGenericService;
import com.atecher.mintools.web.generic.GenericActionController;
import com.atecher.mintools.web.util.WebForwardConstants;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by hanhongwei on 2016/7/19.
 */
@Controller
public class SearchController extends GenericActionController {

    @Autowired
    private IGenericService genericService;
    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public String js(@RequestParam(value="s",required = false) String search, Model model) {
        List<Map> tools=null;
        if(StringUtils.isNotEmpty(search)){
            tools=genericService.selectList("com.atecher.tools.mapper.SearchMapper.search",search);
        }
        model.addAttribute("tools",tools);
        return WebForwardConstants.INDEX;
    }


}
