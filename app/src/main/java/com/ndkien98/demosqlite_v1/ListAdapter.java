package com.ndkien98.demosqlite_v1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends BaseAdapter {

    Context context;
    List<Integer> LIST_ID;
    List<String> LIST_NAME,LIST_ADDRESS,LIST_PHONE;

    public ListAdapter(Context context, ArrayList<Integer> id
                        ,ArrayList<String> name
                        ,ArrayList<String> address
                        ,ArrayList<String> phone){
        this.context=context;
        this.LIST_ID=id;
        this.LIST_NAME=name;
        this.LIST_ADDRESS=address;
        this.LIST_PHONE=phone;
    }


    @Override
    public int getCount() {
        return LIST_ID.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View child, ViewGroup parent) {
        return child;
    }



}

