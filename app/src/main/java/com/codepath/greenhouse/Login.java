package com.codepath.greenhouse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.codepath.greenhouse.Model.UserModel;
import com.codepath.greenhouse.controller.RealmBackupRestore;
import com.codepath.greenhouse.controller.RealmHelper;
import com.codetroopers.betterpickers.datepicker.DatePickerBuilder;
import com.codetroopers.betterpickers.datepicker.DatePickerDialogFragment;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;


public class Login extends AppCompatActivity implements DatePickerDialogFragment.DatePickerDialogHandler {
    private Button btnBirthDate; // c=variables for datepicker birthdate button

    /*Variables for Camera Intent */
    private static final int REQUEST_CODE = 1;
    private Bitmap bitmap;
    private ImageView userImageView;

    /*Radio button for gender */
    private RadioGroup radioSexGroup;
    //private RadioButton radioSexButton;
    private String gender;

    /*spinner */
    private Spinner spinner_Country;

    /*
    Realm
     */
    private Realm realm;
    private RealmHelper realmHelper;
    private RealmBackupRestore realmBackupRestore;

    private EditText eTUserName,eTName, eTPassword, etAge,postCodeEditText;
    //private TextView genderTextView;
    private Button saveButton;

   private String countrySelected;

   private UserModel userModel;
   // private

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        calenderDatePicker();//datepicker
        userImageView = (ImageView) findViewById(R.id.userImageView); //camera intent , set with onclick at layout.xml
        //radiobutton gender
        getGender();
        //spinner
        spinner_Country();
        //Realm
        init();
        initRealm();

    /*    String mUserName = getIntent().getExtras().getString("KEY_USER");
        eTUserName.setText(mUserName);*/
 //       String mUserName = getIntent().getParcelableExtra("KEY_USER");
//        Log.i("mUserName", mUserName);

       // getUserName();

        dummyValues();
        realmBackupRestore = new RealmBackupRestore(this);
    }

    /*
    Calender Date Picker
    */
    public void calenderDatePicker() {
        btnBirthDate = (Button) findViewById(R.id.btnBirthDate);
        btnBirthDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DatePickerBuilder dpb = new DatePickerBuilder()

                        .setFragmentManager(getSupportFragmentManager())

                        .setStyleResId(R.style.BetterPickersDialogFragment);

                dpb.show();
            }
        });
    }

    @Override
    public void onDialogDateSet(int reference, int year, int monthOfYear, int dayOfMonth) {
        //int month = monthOfYear++;
        monthOfYear++;
        btnBirthDate.setText(dayOfMonth + "-" + monthOfYear + "-" + year);
    }

    /*Camera Intent
     */

    public void pickImage(View View) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                // recyle unused bitmaps
                if (bitmap != null) {
                    bitmap.recycle();
                }
                stream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(stream);

                userImageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                if (stream != null)
                    try {
                        stream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
    }

    /*
    Radio Button
     */
    public void getGender() {
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);

        radioSexGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                // This will get the radiobutton that has changed in its check state
                RadioButton checkedRadioButton = (RadioButton) radioGroup.findViewById(i);
                // This puts the value (true/false) into the variable
                boolean isChecked = checkedRadioButton.isChecked();
                // If the radiobutton that has changed in check state is now checked...
                if (isChecked) {
                    gender = (String) checkedRadioButton.getText();
                  //  Log.i("changeradiobutton", gender);
                    //  genderTextView.setText();
                    //  Toast.makeText(this, , Toast.LENGTH_SHORT).show();
                    //       Log.i("change", checkedRadioButton.toString());
                    //   Log.i("change", checkedRadioButton.getText().toString());
                    // Changes the textview's text to "Checked: example radiobutton text"
                    //     tv.setText("Checked:" + checkedRadioButton.getText());
                    //  Toast.makeText(this, "hrey", Toast.LENGTH_SHORT).show();

                    // Log.i("current gender", (String) checkedRadioButton.getText() );

                }
            }
        });

    }

    //Spinner
    public void spinner_Country() {

        spinner_Country = (Spinner) findViewById(R.id.spinner_Country);
        String[] courses = {"Not-Specified", "Malaysia", "United States", "Indonesia", "France", "Italy", "Singapore", "New Zealand", "India"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, courses);
        spinner_Country.setAdapter(adapter);
        spinner_Country.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> av, View v,
                                       int position, long itemId) {
                // TODO Auto-generated method stub
                String item = av.getItemAtPosition(position).toString();
                countrySelected = item;

              //  Toast.makeText(getApplicationContext(), "Selected Item is " + item, Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }
        });
    }


    public void init(){
   /*     private EditText eTUserName,eTName, eTPassword, etAge,postCodeEditText;
        private TextView genderTextView;
        private Button saveButton;*/
        eTName= (EditText) findViewById(R.id.eTName);
        eTUserName= (EditText) findViewById(R.id.eTUserName);
        eTPassword= (EditText) findViewById(R.id.eTPassword);
        etAge = (EditText) findViewById(R.id.etAge);
        postCodeEditText= (EditText) findViewById(R.id.postCodeEditText);
        //genderTextView = (TextView) findViewById(R.id.genderTextView);

        saveButton =(Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData(view);

            }
        });


    }

    //Databases
    public void initRealm(){
        realm = Realm.getDefaultInstance();
        realmHelper = new RealmHelper(realm);
    }

    public void saveData(View view)  {
        /*
        1.Get the strindDate from editText
        2.change it to Dateformat
        3.pass it to userModel
        * */
        String sDate = btnBirthDate.getText().toString();
        DateFormat  dateFormat = new SimpleDateFormat("dd-MM-yyyy"); ;
        Log.i("btnGetDate", btnBirthDate.getText().toString());
       // Date date = new Date(); //if null, the values reflect null, else initialized it shows todays
        Date date = new Date();
        try {
            date = dateFormat.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Log.i("testVAlueDate", date.toString());

        userModel = new UserModel(eTName.getText().toString(), eTUserName.getText().toString(), eTPassword.getText().toString()
                ,Integer.parseInt(etAge.getText().toString()), date,countrySelected, gender,
                postCodeEditText.getText().toString());



        realmHelper.saveUser(userModel);
        realmBackupRestore.backup();

        Intent intent = new Intent(Login.this
                , Welcome.class);
        startActivity(intent);
    }


    public void dummyValues(){
        eTName.setText("hi");
       eTUserName.setText("test");
        eTPassword.setText("random");
        etAge.setText("18");
        postCodeEditText.setText("Ub5");

    }

/*    public void getUserName(){


    }*/



}
