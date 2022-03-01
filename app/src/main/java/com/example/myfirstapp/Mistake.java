/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.myfirstapp;

import java.io.Serializable;

/**
 *
 * @author ordson
 */
public class Mistake implements Serializable {
    private String lexema;
    private String linea;
    private String columna;
    private String tipo;
    private String descripcion;
    private String ERROR_LEXICO= "Simbolo desconocido";
    private String ERROR_SINTACTICO="Simbolo inesperado ";

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Mistake{");
        sb.append("lexema=").append(lexema);
        sb.append(", linea=").append(linea);
        sb.append(", columna=").append(columna);
        sb.append(", tipo=").append(tipo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", ERROR_LEXICO=").append(ERROR_LEXICO);
        sb.append(", ERROR_SINTACTICO=").append(ERROR_SINTACTICO);
        sb.append('}');
        return sb.toString();
    }
    

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
