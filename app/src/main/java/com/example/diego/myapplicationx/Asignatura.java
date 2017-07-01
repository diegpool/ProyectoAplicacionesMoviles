package com.example.diego.myapplicationx;

import android.provider.ContactsContract;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 29-06-17.
 */

public class Asignatura {
    private String id_asignatura = "";
    private String curso = "";
    private String nombre_asignatura = "";
    private ArrayList<Evaluacion> evaluaciones = new ArrayList<Evaluacion>();

    public Asignatura(){

    }

    public Asignatura(DataSnapshot asignatura){
        for(DataSnapshot params : asignatura.getChildren()){
            switch (params.getKey()){
                case "curso":
                    this.curso = params.getValue().toString();
                    break;
                case "nombre_asignatura":
                    this.nombre_asignatura = params.getValue().toString();
                    break;
                case "id_asignatura":
                    this.id_asignatura = params.getValue().toString();
                    break;
                case "evaluaciones":
                    if(params.hasChildren()){
                        for(DataSnapshot eval : params.getChildren()){
                            Evaluacion evaluacion = new Evaluacion(eval);
                            this.evaluaciones.add(evaluacion);
                        }
                    }
                    break;
            }
        }
    }

    public Asignatura(String id_asignatura, String curso, String nombre_asignatura, ArrayList<Evaluacion> evaluaciones) {
        this.id_asignatura = id_asignatura;
        this.curso = curso;
        this.nombre_asignatura = nombre_asignatura;
        this.evaluaciones = evaluaciones;
    }

    public String getId_asignatura() {
        return id_asignatura;
    }

    public void setId_asignatura(String id_asignatura) {
        this.id_asignatura = id_asignatura;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getNombre_asignatura() {
        return nombre_asignatura;
    }

    public void setNombre_asignatura(String nombre_asignatura) {
        this.nombre_asignatura = nombre_asignatura;
    }

    public ArrayList<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(ArrayList<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}
