package com.ndkien98.demosqlite_v1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    EditText txtid,txtUsername,txtPassword,txtPhone;
    DatabaseHandler databaseHandler;
    Button button ;



    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtid = findViewById(R.id.txtid);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtPhone = findViewById(R.id.txtPhone);
        button = findViewById(R.id.bt_List);

        databaseHandler = new DatabaseHandler(this);

        Student student = new Student(1,"kien","song phuong","12345");
        databaseHandler.addStudents(student);
        Student student1 = (Student) databaseHandler.getStudents(1);
        txtid.setText(String.valueOf(student1.getId()));
        txtUsername.setText(student1.getName());
        txtPassword.setText(student1.getPhone_number());
        txtPhone.setText(student1.getAddress());
        Student student4 = new Student(4,"kien4","song phuong","12345");
        Student student2 = new Student(2,"kien2","song phuong","12345");
        Student student3 = new Student(3,"kien3","song phuong","12345");
        databaseHandler.addStudents(student2);
        databaseHandler.addStudents(student3);
        databaseHandler.addStudents(student4);

        this.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Main2Activity_List.class);
                startActivity(intent);
            }
        });
    }









}
