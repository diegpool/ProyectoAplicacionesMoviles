package com.example.diego.myapplicationx;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 29-06-17.
 */

public class Apoderado {
    private String nombre_apoderado = "";
    private String apellido_apoderado = "";
    private String rut_apoderado = "";
    private ArrayList<Alumno> alumnos_apoderado = new ArrayList<Alumno>();

    public Apoderado(DataSnapshot apoderado){
        for(DataSnapshot data_ap : apoderado.getChildren()){
            System.out.println(data_ap.getKey());
            switch (data_ap.getKey()){
                case "nombre_apoderado":
                    nombre_apoderado = data_ap.getValue().toString();
                    break;
                case "apellido_apoderado":
                    apellido_apoderado = data_ap.getValue().toString();
                    break;
                case "rut_apoderado":
                    rut_apoderado = data_ap.getValue().toString();
                    break;
                case "alumnos_apoderado":
                    if(data_ap.hasChildren()) {
                        for (DataSnapshot alm_ap : data_ap.getChildren()) {
                            alumnos_apoderado.add(new Alumno(alm_ap));
                        }
                    }
                    break;
            }
        }
    }

    public Apoderado(String nombre_apoderado, String apellido_apoderado, String rut_apoderado, ArrayList<Alumno> alumnos_apoderado) {
        this.nombre_apoderado = nombre_apoderado;
        this.apellido_apoderado = apellido_apoderado;
        this.rut_apoderado = rut_apoderado;
        this.alumnos_apoderado = alumnos_apoderado;
    }

    public Apoderado(){

    }

    public String getNombre_apoderado() {
        return nombre_apoderado;
    }

    public void setNombre_apoderado(String nombre_apoderado) {
        this.nombre_apoderado = nombre_apoderado;
    }

    public String getApellido_apoderado() {
        return apellido_apoderado;
    }

    public void setApellido_apoderado(String apellido_apoderado) {
        this.apellido_apoderado = apellido_apoderado;
    }

    public String getRut_apoderado() {
        return rut_apoderado;
    }

    public void setRut_apoderado(String rut_apoderado) {
        this.rut_apoderado = rut_apoderado;
    }

    public ArrayList<Alumno> getAlumnos_apoderado() {
        return alumnos_apoderado;
    }

    public void setAlumnos_apoderado(ArrayList<Alumno> alumnos_apoderado) {
        this.alumnos_apoderado = alumnos_apoderado;
    }

    public ArrayList<Double> getAsistenciasPje() {
        return getAsistenciasPje(0);
    }

    public ArrayList<Double> getAsistenciasPje(int index){
        ArrayList<Double> Porcentaje = new ArrayList<>();
        ArrayList<Dia> Asistencia = alumnos_apoderado.get(index).getAsistencia();
        Double Si = 0.0;
        Double No = 0.0;
        for(Dia dia : Asistencia){
            if (dia.getPresente().equals("1")){
                Si++;
            }else{
                No++;
            }
        }
        Porcentaje.add(Si/Asistencia.size() * 100.0);
        Porcentaje.add(No/Asistencia.size() * 100.0);
        return Porcentaje;
    }

    public ArrayList<Dia> getAsistencia(){
        return getAlumnos_apoderado().get(0).getAsistencia();
    }

    public int cantidadAlumnos(){
        return alumnos_apoderado.size();

    }
    public int cantidadAsignaturas (int index){
        ArrayList<Asignatura> asignatura = alumnos_apoderado.get(index).getAsignaturas();
        return asignatura.size();

    }

    public int getCantidadNotasEnEvaluacion(int index, int asignatura){
        ArrayList<Evaluacion> evaluacion = alumnos_apoderado.get(index).getAsignaturas().get(asignatura).getEvaluaciones();

        return evaluacion.size();

    }

    public ArrayList<String> getTodasAsignaturas(int index){
        ArrayList<String> miau = new ArrayList<String>();
        ArrayList<Asignatura> asignaturas = alumnos_apoderado.get(index).getAsignaturas();

        for(Asignatura asignatura : asignaturas){
            miau.add(asignatura.getNombre_asignatura());
        }
        return miau;
    }

    public  String[][] getNombreEvaluacion(int index, int asignatura){
        int cantNotas = getCantidadNotasEnEvaluacion(index,asignatura);
        int cantAsig = cantidadAsignaturas(index);
        String[][] miau = new String[cantAsig][cantNotas];
        ArrayList<Evaluacion> evaluacion;

        for(int i = 0 ; i < cantAsig ; i++){
            evaluacion = alumnos_apoderado.get(index).getAsignaturas().get(i).getEvaluaciones();
            for(int j = 0 ; j < cantNotas ; j++){
                miau[i][j] = evaluacion.get(j).getNombre_evaluacion();

            }
        }
        return miau;
    }

    public  String[][] getNotasEvaluacion(int index, int asignatura){
        int cantNotas = getCantidadNotasEnEvaluacion(index,asignatura);
        int cantAsig = cantidadAsignaturas(index);
        String[][] miau = new String[cantAsig][cantNotas];
        ArrayList<Evaluacion> evaluacion;

        for(int i = 0 ; i < cantAsig ; i++){
            evaluacion = alumnos_apoderado.get(index).getAsignaturas().get(i).getEvaluaciones();
            for(int j = 0 ; j < cantNotas ; j++){
                miau[i][j] = Double.toString(evaluacion.get(j).getNota());

            }
        }
        return miau;
    }
}
