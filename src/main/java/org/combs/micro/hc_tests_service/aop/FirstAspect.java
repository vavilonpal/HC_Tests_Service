package org.combs.micro.hc_tests_service.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class FirstAspect {
    /*
     *
     *  @within - check annotation on the class level
     * */
    @Pointcut("@within(org.springframework.stereotype.Controller)")
    public void isControllerLayer() {

    }

    /*
     *
     * within - check class type name
     * */

    @Pointcut("within(v.*Service)")
    public void isServiceLayer() {
    }

    /*
     *
     * this - check AOP proxy class type
     * target - check target object class type
     */
    @Pointcut("this(org.springframework.data.repository.Repository)")
    //@Pointcut("target(org.springframework.data.repository.Repository)")
    public void isRepositoryLayer() {
    }

    /*
     * annotation - check annotation on method level
     *
     * */
    @Pointcut("isControllerLayer() && @annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void hasGetMapping() {

    }

    /*
     * args - check method param type
     *  * - any param type
     *  .. - 0+ any params type
     * */
    @Pointcut("isControllerLayer() && args(org.springframework.ui.Model,..)")
    public void hasModelParam() {
    }


    /*
     * @args - check annotation on the param type
     *  * - any param type
     *  .. - 0+ any params type
     * */
    @Pointcut("isServiceLayer() && @args(jakarta.persistence.Entity)")
    public void hasEntityParamAnnotation() {
    }

    /*
     *
     * bean - check bean name
     * */
    @Pointcut("bean(*Service)")
    public void isServiceLayerBean() {
    }

    /*
     *
     *
     * */
    @Pointcut("execution(public * org.combs.micro.hc_tests_service.service.*Service.findById(*))")
    public void anyFindByIdServiceMethod() {
    }

    @Before(value = "anyFindByIdServiceMethod()" +
            "&& args(id) " +
            "&& target(service) " +
            "&& this(serviceProxy)",
            argNames = "joinPoint,id,service,serviceProxy")
    public void addLogging(JoinPoint joinPoint,
                           Object id,
                           Object service,
                           Object serviceProxy) {


        log.info("invoked findById method");
    }

    @AfterReturning(
            value = "anyFindByIdServiceMethod()"+
            "&& target(service)",
            returning = "result",
            argNames = "service,result")
    public void addLoggingAfterReturning(Object service,
                                         Object result) {
        log.info(" after returning - invoked findById method");
    }

}
