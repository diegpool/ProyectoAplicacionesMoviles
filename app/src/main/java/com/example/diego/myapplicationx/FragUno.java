package com.example.diego.myapplicationx;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import java.util.ArrayList;


public class FragUno extends Fragment {
    public TextView texto;
    public Button boton;
    public Apoderado ap;
    private PieChart pieChart;

    ArrayList<Dia> Asist;
    private GridView gridView;
    private GridAdapterAsis adapter;
    float w,h;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ap = ((MainActivityFinal)getActivity()).getApoderado();
        View rootView = inflater.inflate(R.layout.fragment_frag_uno, container, false);
        String n_alumno = ap.getAlumnos_apoderado().get(0).getNombre_alumno() + " " + ap.getAlumnos_apoderado().get(0).getApellido_alumno();
        getActivity().setTitle("Asistencia "+n_alumno);
        PieChart chart = (PieChart) rootView.findViewById(R.id.pieChart);
        chart = configureChart(chart);
        chart = setData(chart);
        chart.animateXY(1500, 1500);
        Asist = ap.getAsistencia();
        gridView = (GridView) rootView.findViewById(R.id.gridasis);
        adapter = new GridAdapterAsis(getContext(),Asist);
        gridView.setAdapter(adapter);
        gridView.setNumColumns(1);


        return rootView;

    }

    public PieChart configureChart(PieChart chart) {
        chart.setHoleColor(getResources().getColor(android.R.color.background_dark));
        chart.setHoleRadius(60f);
        chart.setDescription("");
        chart.setTransparentCircleRadius(5f);
        chart.setDrawCenterText(true);
        chart.setDrawHoleEnabled(false);
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);
        chart.setUsePercentValues(true);
        chart.setCenterText("% Asistencia");
        return chart;
    }

    private PieChart setData(PieChart chart) {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();
        ArrayList<Double> asis = ap.getAsistenciasPje();
        int presente = asis.get(0).intValue();
        int ausente = asis.get(1).intValue();
        yVals1.add(new Entry(presente, 0));
        yVals1.add(new Entry(ausente, 1));
        ArrayList<String> xVals = new ArrayList<String>();
        xVals.add("Presente");
        xVals.add("Ausente");
        PieDataSet set1 = new PieDataSet(yVals1, "");
        set1.setSliceSpace(0f);
        ArrayList<Integer> colors = new ArrayList<Integer>();
        colors.add(getResources().getColor(android.R.color.holo_green_light));
        colors.add(getResources().getColor(android.R.color.holo_red_light));
        set1.setColors(colors);
        PieData data = new PieData(xVals, set1);
        chart.setData(data);
        chart.highlightValues(null);
        chart.setCenterTextSize(15);
        chart.invalidate();
        return chart;
    }

    private void DisplayToast(String mensaje) {
        Toast.makeText(getContext(), mensaje, Toast.LENGTH_SHORT).show();
    }


}
