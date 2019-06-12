package com.atecher.mintools.web.controller;

import com.atecher.mintools.model.MtResource;
import com.atecher.mintools.service.IDocumentService;
import com.atecher.mintools.web.util.WebForwardConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by hanhongwei on 2016/7/19.
 */
@Controller
public class DocumentController {

    @Autowired
    private IDocumentService documentService;

    @RequestMapping(value = "/doc", method = RequestMethod.GET)
    public String index(Model model) {
        List<MtResource> docs = documentService.findDocAll();
        model.addAttribute("docs", docs);
        return WebForwardConstants.DOCUMENT_INDEX;
    }


}