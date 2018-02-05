package com.codepath.greenhouse;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by uchit on 04/02/2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
         configRealm();
    }
    public void configRealm(){
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(1) //if i have only one column, drop existing structure of table, add new one but not erasing the  value of the existing table , droping is different than deleting
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
