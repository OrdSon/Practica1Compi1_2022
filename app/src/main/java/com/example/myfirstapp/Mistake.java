/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.myfirstapp;

/**
 *
 * @author ordson
 */
public class Mistake {
    private String lexema;
    private String linea;
    private String columna;
    private String tipo;
    private String descripcion;
    private String ERROR_LEXICO= "Simbolo no admitido en el lenguaje";
    private String ERROR_SINTACTICO="Se esperaba: ";
    

    public Mistake(String lexema, String linea, String columna, String tipo) {
        this.lexema = lexema;
        this.linea = linea;
        this.columna = columna;
        this.tipo = tipo;
        if (tipo.equalsIgnoreCase("Lexico")) {
            this.descripcion = ERROR_LEXICO;
        }else if (tipo.equalsIgnoreCase("Sintactico")) {
            this.descripcion = ERROR_SINTACTICO;
        }
    }

    public Mistake(String mensaje, String lexema) {
        this.tipo = "Semantico";
        this.linea="";
        this.columna="";
        this.lexema =lexema;
        descripcion = mensaje;
    }
    
    
    
    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /*
    */
}
