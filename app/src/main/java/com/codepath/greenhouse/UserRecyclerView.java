package com.codepath.greenhouse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.codepath.greenhouse.Model.UserModel;
import com.codepath.greenhouse.controller.RealmHelper;

import java.util.ArrayList;

import io.realm.Realm;

public class UserRecyclerView extends AppCompatActivity {

   private Realm realm;
   private RealmHelper realmHelper;
   private ArrayList<UserModel> arrayList;
   private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_recycler_view);

        initRealm();
        initRecycleView();
    }
    private void initRealm() {
        realm = realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
        arrayList = realmHelper.getUsers();
    }
    private void initRecycleView() {
        recyclerView =(RecyclerView) findViewById(R.id.rvUsers);
     // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        recyclerView.setAdapter(new UserAdapter(getApplicationContext(), R.layout.row_user, arrayList));

    }
}
