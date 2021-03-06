package com.atecher.mintools.service.impl;

import com.atecher.mintools.mapper.DocMapper;
import com.atecher.mintools.model.MtResource;
import com.atecher.mintools.service.IDocumentService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: hanhongwei
 * @Description:
 * @Date: Created on 2017/12/13
 */
@Service
public class DocumentServiceImpl implements IDocumentService {
    @Resource
    private DocMapper docMapper;

    @Cacheable(value = {"caffeineMintoolsCache"}, key = "#root.targetClass + #root.methodName")
    @Override
    public List<MtResource> findDocAll() {
        return docMapper.findDocAll();
    }
}
