package com.atecher.mintools.mapper;

import java.util.HashMap;
import java.util.List;

/**
 * Created by hanhongwei on 2017/8/14.
 */
public interface DocMapper {
    /**
     *
     * @return
     */
    List<HashMap<String, Object>> findDocAll();
}
