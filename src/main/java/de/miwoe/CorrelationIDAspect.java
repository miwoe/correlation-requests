package de.miwoe;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by mwoelm on 18.09.17.
 */
@Aspect
@Component
public class CorrelationIDAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());


    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && within(de.miwoe..*Controller)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        UUID correlationId = InnerCallsConfig.correlationId.get();
        String serviceName = InnerCallsConfig.service.get();
        String methodName = MethodSignature.class.cast(point.getSignature()).getMethod().getName();
        log.info("{} with correlationId {}, service {} executed", methodName, correlationId, serviceName);
        return point.proceed();
    }
}
