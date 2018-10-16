package com.atecher.mintools.web.controller;

import com.atecher.mintools.mapper.SearchMapper;
import com.atecher.mintools.web.util.WebForwardConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: atecher
 * @date: 2018/10/16 下午4:23
 */
@Controller
public class SearchController {

    @Resource
    private SearchMapper searchMapper;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String js(@RequestParam(value = "s", required = false) String search, Model model) {
        List<HashMap<String, Object>> tools = null;
        if (!StringUtils.isEmpty(search)) {
            tools = searchMapper.search(search);
        }
        model.addAttribute("tools", tools);
        return WebForwardConstants.INDEX;
    }


}
