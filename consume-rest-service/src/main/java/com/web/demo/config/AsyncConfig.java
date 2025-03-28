package com.web.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean(name = "customExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  // Minimum threads
        executor.setMaxPoolSize(10);  // Maximum threads
        executor.setQueueCapacity(20);
        executor.setThreadNamePrefix("AsyncThread-");
        executor.initialize();
        return executor;
    }

    @Bean(name = "apiExecutor")
    public Executor apiExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(8);  // Minimum number of threads
        executor.setMaxPoolSize(16);  // Maximum threads
        executor.setQueueCapacity(50); // Queue size
        executor.setThreadNamePrefix("ApiThread-");
        executor.initialize();
        return executor;
    }
}

