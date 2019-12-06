package com.ndkien98.demosqlite_v1;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;


public class ListAdapter extends BaseAdapter {

    private ArrayList<Student> list;
    private Activity activity;


    public ListAdapter(ArrayList<Student> list, Activity activity) {
        this.list = list;
        this.activity = activity;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // anh xa dua cac item va layout
        LayoutInflater inflater = this.activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_name,null); // danh dau item sang view

        TextView textView_id = view.findViewById(R.id.Item_txt_id); // tao ra text de hien thi tren list view
        TextView textView_name = view.findViewById(R.id.Item_txt_name);
        TextView textView_adress = view.findViewById(R.id.Item_txt_adress);
        TextView textView_phone = view.findViewById(R.id.Item_txt_phone);


        // set gitri khi hien thi len view , txt phai trung vs id trong item
        textView_id.setText(String.valueOf(this.list.get(i).getId()));
        textView_name.setText(this.list.get(i).getName());
        textView_adress.setText(this.list.get(i).getAddress());
        textView_phone.setText(this.list.get(i).getPhone_number());

        return view;
    }
}

