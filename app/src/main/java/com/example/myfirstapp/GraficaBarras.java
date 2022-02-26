/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.myfirstapp;

import java.util.ArrayList;
import java_cup.runtime.Symbol;

/**
 *
 * @author ordson
 */
public class GraficaBarras {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("GraficaBarras{");
        sb.append("titulo=").append(titulo);
        sb.append(", errores=").append(errores);
        sb.append(", elementosX=").append(elementosX);
        sb.append(", elementosY=").append(elementosY);
        sb.append(", unionX=").append(unionX);
        sb.append(", unionY=").append(unionY);
        sb.append('}');
        return sb.toString();
    }

    private String titulo;
    private ArrayList<Symbol> errores = new ArrayList<>();
    private ArrayList<String> elementosX = new ArrayList<>();
    private ArrayList<Double> elementosY = new ArrayList<>();
    private ArrayList<Integer> unionX = new ArrayList<>();
    private ArrayList<Integer> unionY = new ArrayList<>(); 

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<Symbol> getErrores() {
        return errores;
    }

    public void setErrores(ArrayList<Symbol> errores) {
        this.errores = errores;
    }

    public ArrayList<String> getElementosX() {
        return elementosX;
    }

    public void setElementosX(ArrayList<String> elementosX) {
        this.elementosX = elementosX;
    }

    public ArrayList<Double> getElementosY() {
        return elementosY;
    }

    public void setElementosY(ArrayList<Double> elementosY) {
        this.elementosY = elementosY;
    }

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
    
}
