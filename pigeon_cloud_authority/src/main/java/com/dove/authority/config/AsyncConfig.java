package com.dove.authority.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author run
 * @since 2021/3/19 20:09
 */
@Configuration
public class AsyncConfig {

    private static final int MAX_POOL_SIZE = 20;

    private static final int CORE_POOL_SIZE = 10;

    @Bean
    public ExecutorService executorService() {
        ExecutorService threadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(MAX_POOL_SIZE - CORE_POOL_SIZE),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
        return threadPool;
    }
}
