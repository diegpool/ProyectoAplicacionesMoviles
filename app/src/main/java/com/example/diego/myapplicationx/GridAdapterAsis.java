package com.example.diego.myapplicationx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridAdapterAsis extends BaseAdapter {

    private Context context;
    private ArrayList<Dia> arrayList;


    public Context getContext() {
        return context;
    }

    public GridAdapterAsis(Context context, ArrayList<Dia> arrayList ){
        this.context = context;

        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_grid_asis,null);
        }
        TextView titulotv = (TextView) convertView.findViewById(R.id.texto_grid);
        ImageView img = (ImageView) convertView.findViewById(R.id.img_miau);
        Dia dia = arrayList.get(position);
        String fecha = dia.getFecha();
        String presente = dia.getPresente();
        titulotv.setText(fecha);
        switch (presente){
            case("1"):
                img.setImageResource(R.drawable.pres);
                break;
            case("0"):
                img.setImageResource(R.drawable.aus);
                break;
        }

        return convertView;
    }


}
