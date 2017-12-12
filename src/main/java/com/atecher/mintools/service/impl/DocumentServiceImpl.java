package com.atecher.mintools.service.impl;

import com.atecher.mintools.mapper.DocMapper;
import com.atecher.mintools.service.IDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: hanhongwei
 * @Description:
 * @Date: Created on 2017/12/13
 */
@Service
public class DocumentServiceImpl implements IDocumentService {
    @Autowired
    private DocMapper docMapper;
    @Cacheable(value = {"caffeineMintoolsCache"}, key = "#root.targetClass + #root.methodName")
    @Override
    public List<HashMap<String, Object>> findDocAll() {
        return docMapper.findDocAll();
    }
}
