package com.codepath.greenhouse.Model;


import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by uchit on 04/02/2018.
 */

public class UserModel extends RealmObject  {

    public String mName;
    public String mUserName;
    public String mUPassword;
    //photo
    public int Age;
    public Date mDate;
    public String mCountry;
    public String mGender;
    public String mPostcode;

    public UserModel(){

    }

    public UserModel(String mName, String mUserName, String mUPassword, int age, Date mDate, String mCountry, String mGender, String mPostcode) {
        this.mName = mName;
        this.mUserName = mUserName;
        this.mUPassword = mUPassword;
        Age = age;
        this.mDate = mDate;
        this.mCountry = mCountry;
        this.mGender = mGender;
        this.mPostcode = mPostcode;
    }





    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmUserName() {
        return mUserName;
    }

    public void setmUserName(String mUserName) {
        this.mUserName = mUserName;
    }

    public String getmUPassword() {
        return mUPassword;
    }

    public void setmUPassword(String mUPassword) {
        this.mUPassword = mUPassword;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmGender() {
        return mGender;
    }

    public void setmGender(String mGender) {
        this.mGender = mGender;
    }

    public String getmPostcode() {
        return mPostcode;
    }

    public void setmPostcode(String mPostcode) {
        this.mPostcode = mPostcode;
    }


}
