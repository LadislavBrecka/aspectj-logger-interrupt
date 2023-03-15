package org.example.aspect;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.example.Car;

@Aspect
public class Log4jAspect {

  @Around(value = "execution(* org.slf4j.Logger.*(String, ..)) && args(msg, ..)")
  public Object formatMessageLog(String msg, ProceedingJoinPoint joinPoint) throws Throwable {
    Object[] args = joinPoint.getArgs();

    if (args.length > 1) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      args[0] = "[CONVERTING] " + msg;
      args =
          Arrays.stream(args)
              .map(
                  arg -> {
                    if (arg instanceof Car) {
                      arg = gson.toJson(arg);
                    }
                    return arg;
                  })
              .toArray();
    } else {
      args[0] = "[NOTHING] " + msg;
    }
    return joinPoint.proceed(args);
  }
}
