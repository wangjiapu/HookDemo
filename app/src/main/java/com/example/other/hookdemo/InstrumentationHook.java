package com.example.other.hookdemo;

import android.app.Activity;
import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.IBinder;
import android.util.Log;

public class InstrumentationHook extends Instrumentation {
    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent) throws InstantiationException,
            IllegalAccessException, ClassNotFoundException {

        Activity activity=createActivity(intent);
        if (activity!=null){
            return activity;
        }
        return super.newActivity(cl, className, intent);
    }

    private Activity createActivity(Intent intent) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        String className=intent.getComponent().getClassName();
        if (className.equals("com.example.other.hookdemo.MainActivity")){
            Class<?  extends Activity> clazz= (Class<? extends Activity>) Class.
                    forName("com.example.other.hookdemo.TextActivity");
            return clazz.newInstance();
        }

        return null;

    }

    @Override
    public Activity newActivity(Class<?> clazz, Context context,
                                IBinder token, Application application,
                                Intent intent, ActivityInfo info, CharSequence title,
                                Activity parent, String id, Object lastNonConfigurationInstance)
            throws InstantiationException, IllegalAccessException {

        Log.e("newActivity1", " CustomInstrumentation#newActivity call 1");
        return super.newActivity(clazz, context, token, application, intent, info, title, parent,
                id, lastNonConfigurationInstance);
    }
}
