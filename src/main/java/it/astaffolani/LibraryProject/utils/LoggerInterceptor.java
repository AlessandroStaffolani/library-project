package it.astaffolani.LibraryProject.utils;

import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;


public class LoggerInterceptor {

    @AroundInvoke
    public Object logExecutionTime(InvocationContext invocationContext) {
        Long start = System.nanoTime();
        Object returnVal = null;
        try {
            returnVal = invocationContext.proceed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Long end = System.nanoTime();
        String logMessage = invocationContext.getTarget().getClass().getName() + "."
                + invocationContext.getMethod().getName()
                + " execution time = " + (end - start) + " nanoseconds";
        System.out.println(logMessage);
        return returnVal;
    }
}
