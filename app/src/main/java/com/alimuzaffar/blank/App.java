package com.alimuzaffar.blank;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.alimuzaffar.blank.di.component.AppComponent;
import com.alimuzaffar.blank.di.component.DaggerAppComponent;
import com.alimuzaffar.blank.di.component.DaggerObjComponent;
import com.alimuzaffar.blank.di.component.ObjComponent;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;

public class App extends Application implements HasActivityInjector {
    private static App app;
    private static AppComponent appComponent;
    private static ObjComponent objComponent;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        appComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);

        // Use this to inject plain old objects if needed
        objComponent = DaggerObjComponent.builder().appComponent(appComponent).build();
    }

    public String getBaseUrl() {
        return null;
    }

    public static Context getContext() {
        return app.getBaseContext();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static ObjComponent getObjComponent() {
        return objComponent;
    }
}
