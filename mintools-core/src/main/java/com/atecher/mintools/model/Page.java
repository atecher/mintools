package com.atecher.mintools.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
/**
 * @description:
 * @author: atecher
 * @date: 2018/10/16 下午4:23
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page<T> implements Serializable {

    private static final long serialVersionUID = 2438037063942588690L;
    private int total;
    private List<T> rows;

}
