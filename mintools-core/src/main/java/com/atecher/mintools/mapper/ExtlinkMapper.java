package com.atecher.mintools.mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: atecher
 * @date: 2018/10/16 下午4:23
 */
public interface ExtlinkMapper {

    List<String> queryExtlinkForPage(HashMap<String, Object> param);

    int queryExtlinkForPageCount(HashMap<String, Object> param);
}
