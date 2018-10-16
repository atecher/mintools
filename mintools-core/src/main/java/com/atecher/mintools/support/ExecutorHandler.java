package com.atecher.mintools.support;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: hanhongwei
 * @date: 2018/10/16 下午4:52
 */
public class ExecutorHandler {


    public static final ExecutorService newFixedThreadPool(int nThreads) {

        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

    }
}
