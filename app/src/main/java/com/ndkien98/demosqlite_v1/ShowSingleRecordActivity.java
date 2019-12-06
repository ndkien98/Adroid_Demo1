package com.ndkien98.demosqlite_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowSingleRecordActivity extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    TextView txtSingleID,txtSingleName,txtSingleAdrress,txtSinglePhone;

    Student student;
    Button delete,edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);
        // lay id tưừ list view thong qua key "idStduent" de hien thi len edit
        final Intent intent = getIntent();
        int id = (int) intent.getExtras().get("idStduent");

        databaseHandler = new DatabaseHandler(this);

        student = databaseHandler.getStudents(id);

        this.txtSingleID = findViewById(R.id.id_single);
        this.txtSingleName = findViewById(R.id.name_Single);
        this.txtSingleAdrress = findViewById(R.id.adress_Single);
        this.txtSinglePhone = findViewById(R.id.phone_Single);
        this.edit=findViewById(R.id.edit_bt_save);
        this.delete=findViewById(R.id.edit_Delete);

        txtSingleID.setText(String.valueOf(student.getId()));
        txtSingleName.setText(student.getName());
        txtSingleAdrress.setText(student.getAddress());
        txtSinglePhone.setText(student.getPhone_number());

        this.edit.setOnClickListener (new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1Edit = new Intent(ShowSingleRecordActivity.this,EditStudent.class);
                intent1Edit.putExtra("id_student_edit",student.getId());
                startActivity(intent1Edit);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHandler.delete(student.getId());
                Intent intent1 = new Intent(ShowSingleRecordActivity.this,Main2Activity_List.class);
                startActivity(intent1);
            }
        });

    }




}
