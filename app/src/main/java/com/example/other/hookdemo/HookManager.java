package com.example.other.hookdemo;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HookManager {

    static Object activityThreadInstance;

    public static void init() throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Class<?> clazz=Class.forName("android.app.ActivityThread");
        Method currentActivityThread=clazz.getDeclaredMethod("currentActivityThread");
        activityThreadInstance=currentActivityThread.invoke(null);
    }

    public static void injectInstrumentation() throws NoSuchFieldException, IllegalAccessException {
        Field field_instrumentation=activityThreadInstance.getClass()
                .getDeclaredField("mInstrumentation");
        field_instrumentation.setAccessible(true);
        InstrumentationHook instrumentationHook=new InstrumentationHook();
        field_instrumentation.set(activityThreadInstance,instrumentationHook);
    }

}
