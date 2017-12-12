package com.example.faroukjabberi.tagthebus.application;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import io.realm.Realm;

/**
 * Created by farouk.jabberi on 08/12/2017.
 */

public class TagTheBusApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Fresco
        Fresco.initialize(this);
        // Initialize Realm
        Realm.init(this);

    }
}
