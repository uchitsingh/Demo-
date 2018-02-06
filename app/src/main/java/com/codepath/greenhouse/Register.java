package com.codepath.greenhouse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.codepath.greenhouse.util.Constraints;

public class Register extends AppCompatActivity {

    private EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
    }

    //Also an example of Explict Intent, because we are still in same application, switching from Activity B to Activity A
    public void welcomePage(View View){
        Intent intent = new Intent(this, Login.class);
        intent.putExtra(Constraints.KEY_USER, emailEditText.getText().toString()); //passing username data to welcome page
      //  Log.i("passUserName",emailEditText.getText().toString());
        startActivity(intent);
    }
    public void goBack(View view){
        moveTaskToBack(true);
    }
}
