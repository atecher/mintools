package com.atecher.mintools.service;

import java.util.HashMap;
import java.util.List;

/**
 * @Author: atecher
 * @Description:
 * @Date: Created on 2017/12/13
 */
public interface IToolService {

    List<HashMap<String, Object>> findToolAll();

    List<HashMap<String, Object>> findToolsByCategory(String param);
}
