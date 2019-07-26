package it.astaffolani.LibraryProject.utils;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Named;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
@Singleton
public class LoggerProduces {

    @Produces
    public Logger produceLogger(InjectionPoint injectionPoint) {
        return LogManager.getLogger(injectionPoint.getAnnotated().getClass().getName());
    }
}
