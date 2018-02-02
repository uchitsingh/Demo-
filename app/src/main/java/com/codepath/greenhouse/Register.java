package com.codepath.greenhouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


    }

    //Also an example of Explict Intent, because we are still in same application, switching from Activity B to Activity A

    public void goBack(View view){
        moveTaskToBack(true);
    }
}
