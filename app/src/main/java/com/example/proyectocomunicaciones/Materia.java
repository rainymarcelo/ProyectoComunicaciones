package com.example.proyectocomunicaciones;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Materia {

    private String nombreMateria="";
    private int creditos=0;
    private double nota=0;

    public Materia(String nombreMateria, int creditos, double nota) {
        this.nombreMateria = nombreMateria;
        this.creditos = creditos;
        this.nota=nota;

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

    public double getEvaluaciones() {
        return nota;
    }

    public void setEvaluaciones(int evaluaciones) {
        this.nota = evaluaciones;
    }
}
