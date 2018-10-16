package com.atecher.mintools.web.util.extlink;

import com.atecher.mintools.model.Page;

/**
 * @description:
 * @author: atecher
 * @date: 2018/10/16 下午4:23
 */
public class PageResult extends Page {

    private Integer page;
    private Integer limit;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
