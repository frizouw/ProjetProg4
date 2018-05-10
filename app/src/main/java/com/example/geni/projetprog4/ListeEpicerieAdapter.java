package com.example.geni.projetprog4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ListeEpicerieAdapter extends ArrayAdapter<ItemEpicerie>
{
    private Context context;
    private ArrayList<ItemEpicerie> liste;
    public ListeEpicerieAdapter(@NonNull Context context, ArrayList<ItemEpicerie> liste)
    {
        super(context, R.layout.item_epicerie, liste);
        this.context = context;
        this.liste = liste;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        View vi = convertView;
        if (vi == null)
            vi = LayoutInflater.from(context).inflate(R.layout.item_epicerie, parent,false);

        CheckBox box = (CheckBox)vi.findViewById(R.id.list_view_item_checkbox);
        TextView text = (TextView) vi.findViewById(R.id.list_view_item_text);

        ItemEpicerie item = getItem(position);
        box.setChecked(item.isChecked);
        text.setText(item.nom);

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ItemEpicerie item = getItem(position);
                item.isChecked = isChecked;
            }
        });

        return vi;
    }
}
