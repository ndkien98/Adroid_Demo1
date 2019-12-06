package com.ndkien98.demosqlite_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity_List extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    ListView listView;
    ArrayList<Student> list;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2__list);
        databaseHandler = new DatabaseHandler(this);
        list = databaseHandler.getAllStudents();

        listAdapter = new ListAdapter(list,this);
        listView = findViewById(R.id.listview1);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(getApplicationContext(),ShowSingleRecordActivity.class);
                intent.putExtra("idStduent",list.get(position).getId());
                startActivity(intent);
            }
        });


    }

//    public void showDatab(){
//
//        sqLiteDatabase = databaseHandler.getWritableDatabase();
//        cursor = sqLiteDatabase.rawQuery("select * from "+DatabaseHandler.TABLE_NAME+"",null);
//
//        if(cursor.moveToFirst()){
//            do {
//                listId.add(cursor.getInt(cursor.getColumnIndex(DatabaseHandler.KEY_ID)));
//                listName.add(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_NAME)));
//                listAdress.add(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_ADDRESS)));
//                listPhone.add(cursor.getString(cursor.getColumnIndex(DatabaseHandler.KEY_PHONR_NUMBER)));
//            }while (cursor.moveToNext());
//        }
//        listAdapter =new ListAdapter(Main2Activity_List.this,listId,listName,listAdress,listPhone);
//        listView.setAdapter(listAdapter);
//        cursor.close();
//    }


}
