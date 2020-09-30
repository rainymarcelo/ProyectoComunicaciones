package com.example.proyectocomunicaciones;

public class Notas {

    private String nombreActividad="";
    private double nota=0;
    private int porcentaje=0;

    public Notas(String nombreActividad, double nota, int porcentaje) {
        this.nombreActividad = nombreActividad;
        this.nota = nota;
        this.porcentaje = porcentaje;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(int porcentaje) {
        this.porcentaje = porcentaje;
    }
}
