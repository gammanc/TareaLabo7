package com.gamma.tarealabo7.Beans;

/**
 * Created by emers on 22/5/2018.
 */

public class Nota {
    private String carnet, materia, catedratico;
    private double nota;

    public Nota(String carnet, String materia, String catedratico, double nota) {
        this.carnet = carnet;
        this.materia = materia;
        this.catedratico = catedratico;
        this.nota = nota;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
