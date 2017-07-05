package com.example.diego.myapplicationx;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by diego on 04-07-17.
 */

public class GridAdapterCom extends BaseAdapter {

    private Context context;
    private ArrayList<Comunicado> arrayList;


    public Context getContext() {
        return context;
    }

    public GridAdapterCom(Context context, ArrayList<Comunicado> arrayList ){
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
            convertView = layoutInflater.inflate(R.layout.item_grid_com,null);
        }
        TextView titulotv = (TextView) convertView.findViewById(R.id.titulo_grid);
        TextView cuerpotv = (TextView) convertView.findViewById(R.id.texto_grid);
        Comunicado c = arrayList.get(position);
        String Fecha = c.getFecha();
        String Emisor = c.getEmisor();
        String Info = c.getInformacion();
        titulotv.setText("Fecha: "+Fecha +", De: " + Emisor);
        titulotv.setTextSize(15);
        titulotv.setHeight(titulotv.getLineHeight());
        cuerpotv.setText(Info);
        cuerpotv.setTextSize(12);

        return convertView;
    }


}


