package com.example.demo.config.monitor;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.PerformanceMonitorInterceptor;
import org.springframework.lang.Nullable;
import org.springframework.util.StopWatch;

/**
 * 监控性能
 */
public class MonitorInterceptor extends PerformanceMonitorInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Log logger = getLoggerForInvocation(invocation);
        return invokeUnderInfo(invocation, logger);
    }

    protected Object invokeUnderInfo(MethodInvocation invocation, Log logger) throws Throwable {
        String name = createInvocationTraceName(invocation);
        StopWatch stopWatch = new StopWatch(name);
        stopWatch.start(name);
        try {
            return invocation.proceed();
        }
        finally {
            stopWatch.stop();
            writeToLog(logger, stopWatch.shortSummary());
        }
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
