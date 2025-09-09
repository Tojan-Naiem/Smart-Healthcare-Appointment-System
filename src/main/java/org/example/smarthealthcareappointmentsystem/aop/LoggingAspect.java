package org.example.smarthealthcareappointmentsystem.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger= LoggerFactory.getLogger(LoggingAspect.class);
    @AfterReturning("execution(* org.example.smarthealthcareappointmentsystem.service.Impl.AppointmentServiceImp.*.*(..))")
    public void logBookingMethods(JoinPoint joinPoint){
        System.out.println("Booking method called : "+joinPoint.getSignature());

    }
    @AfterReturning("execution(* org.example.smarthealthcareappointmentsystem.service.Impl.PrescriptionServiceImp.*.*(..))")
    public void logPrescriptionMethods(JoinPoint joinPoint){
        System.out.println("Prescription method called : "+joinPoint.getSignature());
    }



}
