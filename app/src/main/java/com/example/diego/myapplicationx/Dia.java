package com.example.diego.myapplicationx;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by diego on 30-06-17.
 */

public class Dia {
    private String fecha;
    private String presente;

    public Dia(DataSnapshot dias){
        for(DataSnapshot var : dias.getChildren()){
            switch (var.getKey().toString()){
                case "fecha":
                    this.fecha = var.getValue().toString();
                    break;
                case "presente":
                    this.presente = var.getValue().toString();
                    break;
            }
        }
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPresente() {
        return presente;
    }

    public void setPresente(String presente) {
        this.presente = presente;
    }

    public Dia(String fecha, String presente) {
        this.fecha = fecha;
        this.presente = presente;
    }
}
