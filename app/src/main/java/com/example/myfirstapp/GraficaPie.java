/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.myfirstapp;

import java.util.ArrayList;

/**
 *
 * @author ordson
 */
public class GraficaPie {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GraficaPie{");
        sb.append("unionX=").append(unionX);
        sb.append(", unionY=").append(unionY);
        sb.append(", etiquetas=").append(etiquetas);
        sb.append(", valores=").append(valores);
        sb.append(", Total=").append(Total);
        sb.append(", titulo=").append(titulo);
        sb.append(", extraName=").append(extraName);
        sb.append('}');
        return sb.toString();
    }

    private ArrayList<Integer> unionX = new ArrayList<>();
    private ArrayList<Integer> unionY = new ArrayList<>();
    private ArrayList<String> etiquetas = new ArrayList<>();
    private ArrayList<Double> valores = new ArrayList<>();
    private int Total;
    private String titulo;
    private String extraName;

    public ArrayList<Integer> getUnionX() {
        return unionX;
    }

    public void setUnionX(ArrayList<Integer> unionX) {
        this.unionX = unionX;
    }

    public ArrayList<Integer> getUnionY() {
        return unionY;
    }

    public void setUnionY(ArrayList<Integer> unionY) {
        this.unionY = unionY;
    }

    public ArrayList<String> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(ArrayList<String> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public ArrayList<Double> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Double> valores) {
        this.valores = valores;
    }

    public int getTotal() {
        return Total;
    }

    public void setTotal(int Total) {
        this.Total = Total;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = extraName;
    }
}
