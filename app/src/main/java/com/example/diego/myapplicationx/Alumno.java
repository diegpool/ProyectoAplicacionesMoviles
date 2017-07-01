package com.example.diego.myapplicationx;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by diego on 29-06-17.
 */

public class Alumno {
    private String nombre_alumno = "";
    private String apellido_alumno = "";
    private String rut_alumno = "";
    private ArrayList<Asignatura> asignaturas = new ArrayList<Asignatura>();
    private ArrayList<Dia> asistencia = new ArrayList<Dia>();

    public Alumno(){

    }

    public Alumno(DataSnapshot alumno){
        for(DataSnapshot datos : alumno.getChildren()){
            switch (datos.getKey()){
                case "nombre_alumno":
                    this.nombre_alumno = datos.getValue().toString();
                    break;
                case "apellido_alumno":
                    this.apellido_alumno = datos.getValue().toString();
                    break;
                case "rut_alumno":
                    this.rut_alumno = datos.getValue().toString();
                    break;
                case "asignaturas":
                    if(datos.hasChildren()) {
                        for (DataSnapshot asig : datos.getChildren()) {
                            Asignatura asignatura = new Asignatura(asig);
                            this.asignaturas.add(asignatura);
                        }
                    }
                    break;
                case "asistencia":
                    if(datos.hasChildren()){
                        for(DataSnapshot dias : datos.getChildren()){
                            Dia dia = new Dia(dias);
                            this.asistencia.add(dia);
                        }
                    }
                    break;
            }
        }
    }


    public ArrayList<Dia> getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(ArrayList<Dia> asistencia) {
        this.asistencia = asistencia;
    }

    public Alumno(String nombre_alumno, String apellido_alumno, String rut_alumno, ArrayList<Asignatura> asignaturas) {
        this.nombre_alumno = nombre_alumno;
        this.apellido_alumno = apellido_alumno;
        this.rut_alumno = rut_alumno;
        this.asignaturas = asignaturas;
    }

    public String getNombre_alumno() {
        return nombre_alumno;
    }

    public void setNombre_alumno(String nombre_alumno) {
        this.nombre_alumno = nombre_alumno;
    }

    public String getApellido_alumno() {
        return apellido_alumno;
    }

    public void setApellido_alumno(String apellido_alumno) {
        this.apellido_alumno = apellido_alumno;
    }

    public String getRut_alumno() {
        return rut_alumno;
    }

    public void setRut_alumno(String rut_alumno) {
        this.rut_alumno = rut_alumno;
    }

    public ArrayList<Asignatura> getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(ArrayList<Asignatura> asignaturas) {
        this.asignaturas = asignaturas;
    }
}
