package com.codepath.greenhouse.controller;

import com.codepath.greenhouse.Model.UserModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by uchit on 04/02/2018.
 */

public class RealmHelper {

    Realm realm;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    //Make sure Async
    //do it in seperate Thread
    //uses Executor Framework
    public void saveUser(final UserModel userModel){

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(userModel);
            }
        });

    }

    //read Customer data
    public ArrayList<UserModel> getUsers(){

        ArrayList<UserModel> userModelArrayList = new ArrayList<>();

        //gets the result in realResults, and add it to arrayList
        RealmResults<UserModel> realmResults = realm.where(UserModel.class).findAll();
        for(UserModel userModel: realmResults){
            userModelArrayList.add(userModel);
        }
        return userModelArrayList;

    }



}
