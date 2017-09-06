package com.atecher.mintools.service;

import com.atecher.mintools.model.Page;

import java.util.HashMap;

/**
 * Created by hanhongwei on 2017/9/4.
 */
public interface IWebsiteService {

    Page<String> queryExtlinkForPage(int page, int limit, HashMap<String,Object> parameter);
}
