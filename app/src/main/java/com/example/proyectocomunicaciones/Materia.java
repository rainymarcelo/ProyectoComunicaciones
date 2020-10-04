package com.example.proyectocomunicaciones;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Materia {

    private String nombreMateria="";
    private Integer creditos = 0;
    private double nota = 0;
    private List<Notas> evaluaciones=new ArrayList<>();


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

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public List<Notas> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Notas> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public void addNota(Notas nota){this.evaluaciones.add(nota);}

    public void notaActual(){
        double sum = 0;
        for (Notas nota: evaluaciones) {
            sum += nota.getNota() * (nota.getPorcentaje()/100);
        }
        this.setNota(sum);
    }
}
