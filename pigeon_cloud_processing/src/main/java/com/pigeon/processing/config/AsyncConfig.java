package com.pigeon.processing.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author 小亮
 * @date 2021/8/8  -  10:18
 */
@Configuration
@ConfigurationProperties(prefix = "pigeon.thread.pool")
public class AsyncConfig {

    private int corePoolSize; // 核心线程池大小
    private int maxinumPoolSize; // 最大核心线程池大小
    private int keepAliveTime; // 超时了没有人调用就会释放


    @Bean
    public ExecutorService executorService() {  //真正的线程池接口
        ExecutorService threadPool = new ThreadPoolExecutor( //ThreadPoolExecutor实现子类,其实可以用单线程池也不错
                corePoolSize,
                maxinumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(maxinumPoolSize - corePoolSize),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return threadPool;
    }

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaxinumPoolSize() {
        return maxinumPoolSize;
    }

    public void setMaxinumPoolSize(int maxinumPoolSize) {
        this.maxinumPoolSize = maxinumPoolSize;
    }

    public int getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(int keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }
}