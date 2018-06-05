package com.example.other.hookdemo;

import android.app.Application;

import java.lang.reflect.InvocationTargetException;

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        try {
            HookManager.init();
            HookManager.injectInstrumentation();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onCreate();
    }
}
