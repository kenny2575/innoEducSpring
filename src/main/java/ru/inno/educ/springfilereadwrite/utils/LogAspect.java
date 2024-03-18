package ru.inno.educ.springfilereadwrite.utils;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.StringJoiner;

@Aspect
@Component
@RequiredArgsConstructor
public class LogAspect {

    private final LogWriter logger;

    @Around("@annotation(LogTransformation)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        LocalDateTime begin = LocalDateTime.now();

        Object object = joinPoint.proceed();

        LocalDateTime end = LocalDateTime.now();

        StringJoiner joiner = new StringJoiner(",");

        joiner
                .add(begin.toString())
                .add(end.toString())
                .add(joinPoint.getSignature().toShortString())
                .add(Arrays.toString(joinPoint.getArgs()))
                .add(String.valueOf(object));

        logger.saveLog(joiner.toString());

        return object;

    }
}
