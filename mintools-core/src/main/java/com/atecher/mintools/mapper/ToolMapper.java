package com.atecher.mintools.mapper;

import com.atecher.mintools.model.MtResource;

import java.util.List;

/**
 * Created by hanhongwei on 2017/8/14.
 */
public interface ToolMapper {

    List<MtResource> findToolAll();

    List<MtResource> findToolsByCategory(String param);
}
