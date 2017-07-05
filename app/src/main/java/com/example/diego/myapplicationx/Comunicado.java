package com.example.diego.myapplicationx;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by diego on 03-07-17.
 */

public class Comunicado {
    String Emisor;
    String Fecha;
    String Informacion;

    public Comunicado(String emisor, String fecha, String informacion) {
        Emisor = emisor;
        Fecha = fecha;
        Informacion = informacion;
    }

    public Comunicado(){

    }
    public Comunicado(DataSnapshot dataSnapshot){
        for(DataSnapshot comunicados : dataSnapshot.getChildren()){
            switch (comunicados.getKey()){
                case "emisor":
                    this.Emisor = comunicados.getValue().toString();
                    break;
                case "fecha":
                    this.Fecha = comunicados.getValue().toString();
                    break;
                case "informacion":
                    this.Informacion = comunicados.getValue().toString();
                    break;
            }
        }
    }

    public String getEmisor() {
        return Emisor;
    }

    public void setEmisor(String emisor) {
        Emisor = emisor;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getInformacion() {
        return Informacion;
    }

    public void setInformacion(String informacion) {
        Informacion = informacion;
    }
}
