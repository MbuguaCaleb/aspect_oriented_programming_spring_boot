package demo;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    //the execution is the method i want to call
    //rememember that these methods are being called from a join point
    @Before("execution(* demo.ShoppingCart.checkout(..))")
    public void beforeLogger(JoinPoint jp){
        //System.out.println(jp.getSignature());
        String arg = jp.getArgs()[0].toString();

        System.out.println("Before Loggers with Argument " + arg);
    }

    //the asterisks have meaning
    //The two dots in my method dictate that i should pass the same number of parameters
    @After("execution(* *.*.checkout(..)))")
    public void afterLogger(){
        System.out.println("After Logger");
    }

    //so that my point cut works even if there are parameters
    @Pointcut("execution(* demo.shoppingCart.quantity(..))")
    public  void afterReturningPointCut(){

    }
    @AfterReturning(pointcut = "afterReturningPointCut()",returning = "retVal")
    public void afterReturning(String retval){
        System.out.println("After returning:" + retval);
    }

}
