package com.tukeping.tools.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.asm.Advice;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author tukeping
 * @date 2020/2/11
 **/
public class ByteBuddyTest {
    @LoggerAdvisor.Log
    public int foo(int value) {
        System.out.println("foo: " + value);
        return value;
    }

    public int bar(int value) {
        System.out.println("bar: " + value);
        return value;
    }

    public static void main(String[] args) throws Exception {
        ByteBuddyTest byteBuddyTest = new ByteBuddy()
                .subclass(ByteBuddyTest.class)
                .method(ElementMatchers.any())
                .intercept(Advice.to(LoggerAdvisor.class))
                .make()
                .load(ByteBuddyTest.class.getClassLoader())
                .getLoaded()
                .newInstance();
        byteBuddyTest.bar(123);
        byteBuddyTest.foo(456);
    }
}

class LoggerAdvisor {
    @Advice.OnMethodEnter
    public static void onMethodEnter(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments) {
        if (method.getAnnotation(Log.class) != null) {
            System.out.println("Enter " + method.getName() + " with arguments: " + Arrays.toString(arguments));
        }
    }

    @Advice.OnMethodExit
    public static void onMethodExit(@Advice.Origin Method method, @Advice.AllArguments Object[] arguments, @Advice.Return Object ret) {
        if (method.getAnnotation(Log.class) != null) {
            System.out.println("Exit " + method.getName() + " with arguments: " + Arrays.toString(arguments) + " return: " + ret);
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Log {
    }
}
