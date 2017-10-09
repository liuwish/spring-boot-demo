package com.github.jartisan.springbootdemo.configuration;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SlaveDataSourceInterceptor implements Ordered {

    public static final Logger logger = LoggerFactory.getLogger(SlaveDataSourceInterceptor.class);

    @Around("@annotation(readOnlyConnection)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint,SlaveDataSource readOnlyConnection) throws Throwable {
        try {
            logger.debug("set database connection to read only");
            DbContextHolder.setDbType(DbContextHolder.DbType.SLAVE);
            Object result = proceedingJoinPoint.proceed();
            return result;
        }finally {
            DbContextHolder.clearDbType();
            logger.debug("restore database connection");
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
