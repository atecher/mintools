package com.atecher.mintools.mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: atecher
 * @date: 2018/10/16 下午4:23
 */
public interface ToolMapper {

    List<HashMap<String, Object>> findToolAll();

    List<HashMap<String, Object>> findToolsByCategory(String param);
}
