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

    public ArrayList<Student> getAllStudents(){

        ArrayList<Student> studentList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("select * from %s", TABLE_NAME),null);
        cursor.moveToFirst();

        while (cursor.isAfterLast()== false){

            int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String adress = cursor.getColumnName(cursor.getColumnIndex(KEY_ADDRESS));
            String phone = cursor.getColumnName(cursor.getColumnIndex(KEY_PHONR_NUMBER));
            Student student = new Student(id,name,adress,phone);
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


    public NhanVien getNhanVien(String username,String password){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        //Cursor cursor =sqLiteDatabase.query(TABLE_NHANVIEN,null,KEY_NHAN_VIEN_USERNAME+"=?"+KEY_NHAN_VIEN_PASSWORD+"=?",new String []{username,password},null,null,null);
        Cursor cursor = sqLiteDatabase.rawQuery(String.format("select * from %s where %s='%s' and %s='%s'", TABLE_NHANVIEN,KEY_NHAN_VIEN_USERNAME,username,KEY_NHAN_VIEN_PASSWORD,password),null);
        NhanVien nhanVien = null;
        try {
            if (cursor!=null){
                cursor.moveToNext();
            }
            int id = cursor.getInt(cursor.getColumnIndex(KEY_NHAN_VIEN_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NHAN_VIEN_NAME));
            String usename1 = cursor.getString(cursor.getColumnIndex(KEY_NHAN_VIEN_USERNAME));
            String pass = cursor.getString(cursor.getColumnIndex(KEY_NHAN_VIEN_PASSWORD));
            String diachi = cursor.getString(cursor.getColumnIndex(KEY_NHAN_VIEN_DIACHI));
            String phongban = cursor.getString(cursor.getColumnIndex(KEY_NHAN_VIEN_PHONG_BAN));
            int namsinh = cursor.getInt(cursor.getColumnIndex(KEY_NHAN_VIEN_NAMSINH));
            int luong = cursor.getInt(cursor.getColumnIndex(KEY_NHAN_VIEN_LUONG));
            int quyen  = cursor.getInt(cursor.getColumnIndex(KEY_NHAN_VIEN_QUYEN));
            nhanVien = new NhanVien(id,name,usename1,pass,diachi,phongban,namsinh,luong,quyen);
        }catch (Exception e){
            System.out.println("User null");
        }
        return nhanVien;
    }

}
