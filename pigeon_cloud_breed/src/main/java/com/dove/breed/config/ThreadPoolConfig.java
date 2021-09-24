package com.dove.breed.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;

/**
 * @author zcj
 * @creat 2021-09-20-22:15
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "dove.thread.pool")
public class ThreadPoolConfig {
    private int corePoolSize; // 核心线程池大小
    private int maxinumPoolSize; // 最大核心线程池大小
    private int keepAliveTime; // 超时了没有人调用就会释放

    @Bean
    public ExecutorService executorService() {
        ExecutorService threadPool = new ThreadPoolExecutor(
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
}
