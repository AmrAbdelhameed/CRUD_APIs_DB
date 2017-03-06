package com.example.amr.apisapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Amr on 26/02/2017.
 */

public class CustomList extends ArrayAdapter<String> {

    private String[] ids;
    private String[] names;
    private String[] emails;
    private String[] ages;

    private Activity context;

    public CustomList(Activity context, String[] ids, String[] names, String[] emails, String[] ages) {
        super(context, R.layout.list_view_layout, ids);
        this.context = context;
        this.ids = ids;
        this.names = names;
        this.emails = emails;
        this.ages = ages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.list_view_layout, null, true);

        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewAge = (TextView) listViewItem.findViewById(R.id.textViewAge);

        textViewId.setText("ID : " + ids[position]);
        textViewName.setText("First Name : " + names[position]);
        textViewEmail.setText("Last Name : " + emails[position]);
        textViewAge.setText("Age : " + ages[position]);

        return listViewItem;
    }
}