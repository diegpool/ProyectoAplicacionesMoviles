package com.example.diego.myapplicationx;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class FragDos extends Fragment {
    Apoderado apoderado;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_frag_dos, container, false);
        getActivity().setTitle("Notas");
        apoderado = ((MainActivityFinal)getActivity()).getApoderado();
        TextView miauasignatura = (TextView) rootView.findViewById(R.id.textoasignatura);
        ArrayList<String> asignaturas = apoderado.getTodasAsignaturas(0);
        String[][] NombreNotas = apoderado.getNombreEvaluacion(0,0);
        String[][] Notas = apoderado.getNotasEvaluacion(0,0);
        int CantAsig = apoderado.cantidadAsignaturas(0);
        int CantNotas = apoderado.getCantidadNotasEnEvaluacion(0,0);
        for(int i = 0 ; i < CantAsig ; i++){
            printtext(miauasignatura,"   "+asignaturas.get(i),18,0,0,0);
            printtext(miauasignatura," ",18,255,0,0);
            for (int j = 0 ; j < CantNotas ; j++){
                printtext(miauasignatura,"       "+NombreNotas[i][j]+":   "+Notas[i][j],18,0,0,0);
            }
            printtext(miauasignatura," ",18,0,0,0);
        }

        return rootView;
    }

    public void printtext(TextView textview,String texto, int size, int r, int g, int b){
        String textoanterior = textview.getText().toString();
        textview.setTextColor(Color.argb(255,r,g,b));
        textview.setTextSize(size);
        textview.setText(textoanterior+"\n"+texto);

    }

}
