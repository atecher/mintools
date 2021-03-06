package com.atecher.mintools.service.impl;

import com.atecher.mintools.mapper.ToolMapper;
import com.atecher.mintools.model.MtResource;
import com.atecher.mintools.service.IToolService;
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
public class ToolServiceImpl implements IToolService {
    @Resource
    private ToolMapper toolMapper;

    @Cacheable(value = {"caffeineMintoolsCache"}, key = "#root.targetClass + #root.methodName")
    @Override
    public List<MtResource> findToolAll() {
        return toolMapper.findToolAll();
    }

    @Cacheable(value = {"caffeineMintoolsCache"}, key = "#root.targetClass + #root.methodName + #param")
    @Override
    public List<MtResource> findToolsByCategory(String param) {
        return toolMapper.findToolsByCategory(param);
    }
}
