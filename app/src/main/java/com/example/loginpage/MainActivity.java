package com.example.loginpage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String filename="vikas.txt";
    public static String shareprefname = "Preferences.txt";

    private EditText username,password;
    private TextView userView,passwordView;
    Button submit,retrive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.usrname);
        password = findViewById(R.id.usrPwd);
        userView = findViewById(R.id.usrView);
        passwordView = findViewById(R.id.pwdView);
        submit = findViewById(R.id.subBtn);
        retrive = findViewById(R.id.retBtn);
        submit.setOnClickListener(this);
        retrive.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.subBtn:
//                writeToSharedPreferences("username",username.getText().toString());
//                writeToSharedPreferences("password",password.getText().toString());
                writeToInternalFile(username.getText().toString());
                writeToInternalFile(password.getText().toString());
                break;
            case R.id.retBtn:
                String userInfo[] = readFromInternalFile().split("_");
                userView.setText(userInfo[0]);
                passwordView.setText(userInfo[1]);
                break;
        }
    }

    private void writeToSharedPreferences(String key, String value) {
        SharedPreferences.Editor sharedpref = getSharedPreferences(shareprefname,MODE_PRIVATE).edit();
        sharedpref.putString(key,value);
         sharedpref.commit();
    }

    private void writeToInternalFile(String data) {
       try {
           FileOutputStream fos = openFileOutput(filename,MODE_APPEND);
           PrintWriter print = new PrintWriter(fos);
           print.println(data);
           print.close();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    private String readFromInternalFile() {
        try {
            FileInputStream fin = openFileInput(filename);
            InputStreamReader is =new InputStreamReader(fin);
            BufferedReader bread = new BufferedReader(is);
            String data = bread.readLine() + "_" + bread.readLine();
            bread.close();
            return data;
        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    private String readFromSharedPreference(String key){
        SharedPreferences sharedPref = getSharedPreferences(shareprefname,MODE_PRIVATE);
        return sharedPref.getString(key,"Default");
    }
}


