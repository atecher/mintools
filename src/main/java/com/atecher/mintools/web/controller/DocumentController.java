package com.atecher.mintools.web.controller;

import com.atecher.mintools.service.IGenericService;
import com.atecher.mintools.web.generic.GenericActionController;
import com.atecher.mintools.web.util.WebForwardConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by hanhongwei on 2016/7/19.
 */
@Controller
public class DocumentController extends GenericActionController {

    @Autowired
    private IGenericService genericService;

    @RequestMapping(value = "/doc",method = RequestMethod.GET)
    public String index(Model model) {
        List<Map> docs=genericService.selectList("com.atecher.tools.mapper.DocMapper.findDocAll",null);
        model.addAttribute("docs",docs);
        return WebForwardConstants.DOCUMENT_INDEX;
    }


}