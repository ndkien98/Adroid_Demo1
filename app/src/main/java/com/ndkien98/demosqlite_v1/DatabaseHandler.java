package com.ndkien98.demosqlite_v1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "schoolManager";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "students";

    public static final String KEY_ID="id";
    public static final String KEY_NAME = "name";
    public static final String KEY_ADDRESS = "adress";
    public static final String KEY_PHONR_NUMBER="phonr_number";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }




    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_students_table = String.format("CREATE TABLE %s(%s INTEGER PRIMARY KEY, %s VARCHAR ,%s VARCHAR , %s VARCHAR)",
                                                    TABLE_NAME,KEY_ID,KEY_NAME,KEY_ADDRESS,KEY_PHONR_NUMBER);
        sqLiteDatabase.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        sqLiteDatabase.execSQL(drop_students_table);
        onCreate(sqLiteDatabase);
    }


    public void addStudents (Student student){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_ADDRESS,student.getAddress());
        values.put(KEY_PHONR_NUMBER,student.getPhone_number());

        sqLiteDatabase.insert(TABLE_NAME,null,values);
        sqLiteDatabase.close();
    }


//    public Cursor query(String table,String [] colums,String selection,String[] selectionArgs,String groupBy, String having,String orderBy){
//
//    }

    public Student getStudents(int studentsId){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,null,KEY_ID+"=?",new String[]{String.valueOf(studentsId)},null,null,null);
        if (cursor != null){
            cursor.moveToNext();
        }
        Student student = new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
        return student;
    }

    public List<Student> getAllStudents(){

        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * from"+TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast()== false){
            Student student = new Student(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            studentList.add(student);
            cursor.moveToNext();
        }
        return studentList;
    }

    public void updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME,student.getName());
        values.put(KEY_ADDRESS,student.getAddress());
        values.put(KEY_PHONR_NUMBER,student.getPhone_number());

        db.update(TABLE_NAME,values,KEY_ID +"=?",new String[] {String.valueOf(student.getId())});
        db.close();

    }

    public void delete(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME,KEY_ID+"=?",new String[]{String.valueOf(id)});
        database.close();
    }

}
