package com.codepath.greenhouse;

import android.app.ListActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.codepath.greenhouse.Model.UserModel;
import com.codepath.greenhouse.controller.RealmHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.realm.Realm;

public class Welcome extends ListActivity {
    private Realm realm;
    private RealmHelper realmHelper;
    private ArrayList<UserModel> userModelArrayList;
    private ArrayList<String> stringArrayList;

   // private TextView textView_welcome;
    private String userName = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ListView lv = getListView();
        lv.setDividerHeight(14);
        lv.setBackgroundColor(Color.GRAY);

        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        userModelArrayList = new ArrayList<>();
        /**
         * Get Model
         * Put the model in the Adapter
         * Assign the adapter to the view
         */
        userModelArrayList = realmHelper.getUsers();
        stringArrayList = new ArrayList<>();

        for (int i = 0; i < userModelArrayList.size(); i++) {

            String name = userModelArrayList.get(i).getmName();
            userName = userModelArrayList.get(i).getmUserName();
            //   String uPassword = userModelArrayList.get(i).getmUPassword();
            String age = String.valueOf(userModelArrayList.get(i).getAge());

            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            String sDate = null;
            Date date = new Date();
            try {
                 date = userModelArrayList.get(i).getmDate();
                sDate = format.format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
           // Log.i("date", sDate);


            String country = userModelArrayList.get(i).getmCountry();
            String gender = userModelArrayList.get(i).getmGender();
            String postcode = userModelArrayList.get(i).getmPostcode();

            stringArrayList.add("\t\t\t\t\t\t\t\t\t\t\t\t" + name + "\t\t\t\t\t\t\t\t\t\t\t\t" + gender + "\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t" +  postcode + "\t\t\t\t\t\t\t\t\t\t\t\t" + sDate + "\n" +
                    "\t\t\t\t\t\t\t\t\t\t\t\t" + country);


            //2.put the model in the adapter

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    Welcome.this,
                    android.R.layout.simple_list_item_1,
                    stringArrayList
            );
            //assign the adapter to view
            getListView().setAdapter(arrayAdapter);

        }

        /*Parcelable Implementation"  */
        //textView_welcome = (TextView) findViewById(R.id.textView_Welcome);
        String mUserName = getIntent().getParcelableExtra("KEY_USER");

      //  textView_welcome.setText(mUserName);
      //  Log.i("passUserName", mUserName);


        removeRealmData();

    }

    public void removeRealmData(){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

    // delete all realm objects
        realm.deleteAll();

    //commit realm changes
        realm.commitTransaction();


    }


}
