package com.atecher.mintools.service;

import com.atecher.mintools.model.Page;

import java.util.HashMap;

/**
 * @Author: atecher
 * @Description:
 * @Date: Created on 2017/9/4
 */
public interface IWebsiteService {

    Page queryExtlinkForPage(int page, int limit, HashMap<String, Object> parameter);
}
