package com.atecher.mintools.service.impl;

import com.atecher.mintools.mapper.ExtlinkMapper;
import com.atecher.mintools.model.Page;
import com.atecher.mintools.service.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hanhongwei on 2017/9/4.
 */
@Service
public class WebsiteServiceImpl implements IWebsiteService {

    @Resource
    private ExtlinkMapper extlinkMapper;

    @Override
    @Cacheable(value = {"caffeineMintoolsCache"}, key = "#root.targetClass + #root.methodName + #page + #limit +#parameter")
    public Page<String> queryExtlinkForPage(int page, int limit, HashMap<String, Object> parameter) {
        parameter.put("start", (page - 1) * limit);
        parameter.put("limit", limit);
        int total = extlinkMapper.queryExtlinkForPageCount(parameter);
        if (total == 0) {
            return new Page<>(0, new ArrayList<>());
        } else {
            return new Page<>(total, extlinkMapper.queryExtlinkForPage(parameter));
        }
    }

}
