package com.atecher.mintools.service;

import com.atecher.mintools.model.MtResource;

import java.util.List;

/**
 * @Author: hanhongwei
 * @Description:
 * @Date: Created on 2017/12/13
 */
public interface IToolService {

    List<MtResource> findToolAll();

    List<MtResource> findToolsByCategory(String param);
}
