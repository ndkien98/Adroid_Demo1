package com.ndkien98.demosqlite_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditStudent extends AppCompatActivity {


    DatabaseHandler databaseHandler ;
    EditText editText_name,editText_adress;
    Button edit_bt_save;
    Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        databaseHandler = new DatabaseHandler(this);
        edit_bt_save = findViewById(R.id.edit_bt_save);

        editText_name = findViewById(R.id.editText_name);
        editText_adress = findViewById(R.id.editText_adress);

        int id = (int) getIntent().getExtras().get("id_student_edit");
         student = databaseHandler.getStudents(id);

        editText_name.setText(student.getName());
        editText_adress.setText(student.getAddress());

        edit_bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler.updateStudent(getPropertiStudent(student));
                Intent intent = new Intent(EditStudent.this,Main2Activity_List.class);
                startActivity(intent);
            }
        });



    }


    private Student getPropertiStudent(Student student){
        student.setName(editText_name.getText().toString());
        student.setAddress(editText_adress.getText().toString());
        return student;
    }
}
