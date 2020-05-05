package com.example.demo.config.monitor;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;

@Profile({"dev", "test"})
@Configuration
@EnableAspectJAutoProxy
@Aspect
public class Monitor {
    @Pointcut("execution(public * com.baomidou.mybatisplus.extension.service.IService.*(..)) " +
            "|| execution(public * com.baomidou.mybatisplus.core.mapper.BaseMapper.*(..))")
    public void monitor() {
    }

    @Bean
    public MonitorInterceptor performanceMonitorInterceptor() {
        MonitorInterceptor monitorInterceptor = new MonitorInterceptor();
        monitorInterceptor.setLoggerName("PerformanceMonitorInterceptor");
        return monitorInterceptor;
    }

    @Bean
    public Advisor performanceMonitorAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("com.example.demo.config.monitor.Monitor.monitor()");
        return new DefaultPointcutAdvisor(pointcut, performanceMonitorInterceptor());
    }
}
