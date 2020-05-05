package com.example.demo.config.monitor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.lang.Nullable;

/**
 * 监控性能
 */
public class MonitorInterceptor extends PerformanceMonitorInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Log logger = getLoggerForInvocation(invocation);
        return invokeUnderTrace(invocation, logger);
    }

    @Override
    protected void writeToLog(Log logger, String message, @Nullable Throwable ex) {
        if (ex != null) {
            logger.info(message, ex);
        }
        else {
            logger.info(message);
        }
    }
}
