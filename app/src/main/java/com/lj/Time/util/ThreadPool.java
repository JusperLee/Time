package com.lj.Time.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 获取线程池对象，单例类
 */
public class ThreadPool {

    private static ThreadPool threadPool = null;

    public static ThreadPool getInstance() {
        if(threadPool == null){
            synchronized (ThreadPool.class){
                if(threadPool == null){
                    threadPool = new ThreadPool();
                }
            }
        }
        return threadPool;
    }

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private ThreadPool() {
    }

    public ExecutorService getThreadPool() {
        return executorService;
    }
}
