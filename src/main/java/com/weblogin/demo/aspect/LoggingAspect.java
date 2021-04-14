package com.weblogin.demo.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.weblogin.demo.model.User;
import com.weblogin.demo.service.NotificationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

//
@Aspect
@Component
public class LoggingAspect {

    @Autowired
    private NotificationService notificationService;

    @Pointcut(value = "execution(* com.weblogin.demo.controller.UsersController.updateUser(..))")
    public void myPointcut() {
    }

    @Before(value = "execution(* com.weblogin.demo.service.UserService.*(..)) and args(name,surname,email,id)")
    public void myPointcut2(JoinPoint joinPoint,String name,String surname,String email ,Integer id) {
//        System.out.println("before method:"+joinPoint.getSignature().getName());
//        System.out.println("updating user with : "+name);
//        System.out.println("updating user with : "+surname);
//        System.out.println("updating user with : "+email+"and id:"+id   );
    }


    @After(value = "execution(* com.weblogin.demo.service.UserService.*(..)) and args(name,surname,email,id)")
    public void after(JoinPoint joinPoint, String name, String surname, String email , Integer id) {
        System.out.println("(AOP  says)Updated user is:"+ name+" "+ surname+ "  Email is:"+email);

    }


    /*
    @Around("myPointcut()")
    public Object appLogger(ProceedingJoinPoint pjp) throws Throwable {
        ObjectMapper objectMapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        System.out.println("apploger started:");
      Object[] array = pjp.getArgs();
        String arrays = pjp.getArgs().toString();
        System.out.println(Arrays.toString(array));
        System.out.println(arrays);
        System.out.println("method invoked:");
        System.out.println("ad ve:" + className + "." + methodName);
       System.out.println("values:"+objectMapper.writeValueAsString(array));
        Object obj = pjp.proceed();

        System.out.println("apploger started:2");
        System.out.println(objectMapper.writeValueAsString(array));
      System.out.println("values after proceed:" + objectMapper.writeValueAsString(obj));
        return obj;
    }


    @AfterReturning(pointcut = "myPointcut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        System.out.println("afterreturning started");
        ResponseEntity<User> rs = (ResponseEntity<User>) result;
        User body = rs.getBody();
        System.out.println("maili:"+body.getEmail());
        notificationService.sendEmailWhenUserUpdated(body);
        System.out.println("sent mail! to user ");

    }

    @Before(value = "myPointcut()")
    public void beforeMethod(){
        System.err.println("Method will start in seconds");
    }

    @After(value = "myPointcut()")
    public void afterMethod(){
        System.err.println("Method finished successfully");
    }
   */












//

}
