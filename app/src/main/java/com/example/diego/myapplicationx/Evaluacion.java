package com.example.diego.myapplicationx;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by diego on 29-06-17.
 */

public class Evaluacion {
    String nombre_evaluacion = "";
    double nota;

    public Evaluacion(){

    }

    public Evaluacion(DataSnapshot evaluaciones){
        for(DataSnapshot eval : evaluaciones.getChildren()){
            switch (eval.getKey()){
                case "nombre":
                    this.nombre_evaluacion = eval.getValue().toString();
                    break;
                case "nota":
                    this.nota = Double.parseDouble(eval.getValue().toString());
                    break;
            }
        }
    }

    public Evaluacion(String nombre_evaluacion, double nota) {
        this.nombre_evaluacion = nombre_evaluacion;
        this.nota = nota;
    }

    public String getNombre_evaluacion() {
        return nombre_evaluacion;
    }

    public void setNombre_evaluacion(String nombre_evaluacion) {
        this.nombre_evaluacion = nombre_evaluacion;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
