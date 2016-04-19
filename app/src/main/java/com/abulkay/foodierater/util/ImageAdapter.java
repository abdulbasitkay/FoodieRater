package com.abulkay.foodierater.util;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by akay on 4/3/16.
 */
public class ItemAdapter extends ArrayAdapter<Item> {

    public ItemAdapter(Context c, List<Item> items) {
        super(c, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MenuView.ItemView itemView = (MenuView.ItemView)convertView;
        if (null == itemView)
            itemView = MenuView.ItemView.inflate(parent);
        itemView.setItem(getItem(position));
        return itemView;
    }

}