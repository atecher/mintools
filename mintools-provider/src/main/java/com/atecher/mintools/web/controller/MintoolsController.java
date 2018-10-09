package com.atecher.mintools.web.controller;

import com.atecher.mintools.mapper.ToolMapper;
import com.atecher.mintools.service.IToolService;
import com.atecher.mintools.web.util.WebForwardConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Controller
public class MintoolsController {

    @Autowired
    private IToolService toolService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        List<HashMap<String, Object>> tools = toolService.findToolAll();
        model.addAttribute("tools", tools);
        return WebForwardConstants.INDEX;
    }

    @RequestMapping(value = "/category/{code}", method = RequestMethod.GET)
    public String category(@PathVariable("code") String code, Model model) {
        List<HashMap<String, Object>> tools = toolService.findToolsByCategory(code);
        model.addAttribute("tools", tools);
        return WebForwardConstants.INDEX;
    }

}
