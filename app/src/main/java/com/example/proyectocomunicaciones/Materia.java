package com.example.proyectocomunicaciones;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Materia {

    private String nombreMateria="";
    private int creditos=0;
    private int evaluaciones=0;

    public Materia(String nombreMateria, int creditos) {
        this.nombreMateria = nombreMateria;
        this.creditos = creditos;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public int getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(int evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}
