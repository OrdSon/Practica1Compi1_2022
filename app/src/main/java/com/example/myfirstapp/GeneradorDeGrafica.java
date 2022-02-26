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
public class GeneradorDeGrafica {

    boolean barras = false;
    boolean pie = false;
    boolean cantidad = false;
    boolean porcentaje = false;
    String titulo;
    String extraName;
    int total;
    ArrayList<Symbol> errores = new ArrayList<>();
    ArrayList<String> elementosX = new ArrayList<>();
    ArrayList<Double> elementosY = new ArrayList<>();
    ArrayList<Integer> unionX = new ArrayList<>();
    ArrayList<Integer> unionY = new ArrayList<>();
    ArrayList<String> etiquetas = new ArrayList<>();
    ArrayList<Double> valores = new ArrayList<>();
    ArrayList<String> warnings = new ArrayList<>();
    private ArrayList<GraficaBarras> graficasDeBarras = new ArrayList<>();
    private ArrayList<GraficaPie> graficasDePie = new ArrayList<>();

    void setTipoBarras() {
        if (!barras && !pie) {
            this.barras = true;
        } else {
            System.out.println("error");
        }
    }

    void setTipoPie() {
        if (!barras && !pie) {
            this.pie = true;
        } else {
            System.out.println("error");
        }
    }

    void setTitulo(String title) {
        if (this.titulo == null || this.titulo.isEmpty()) {
            this.titulo = title;
        } else {
            System.out.println("error, titulo repetido");
        }
    }

    void setTipoCantidad() {
        if (pie && !porcentaje && !cantidad) {
            this.cantidad = true;
        } else {
            System.out.println("error, el tipo ya se habia definido");
        }
    }

    void setTipoPorcentaje() {
        if (pie && !porcentaje && !cantidad) {
            this.porcentaje = true;
        } else {
            System.out.println("error, el tipo ya se habia definido");
        }
    }

    void addXElement(String id) {
        this.elementosX.add(id);
    }

    void addYElement(String decimal) {
        try {
            this.elementosY.add(Double.parseDouble(decimal));
        } catch (NumberFormatException e) {
            System.out.println("Error: el valor recibido es del tipo incorrecto");
        }
    }

    void addUnion(String x, String y) {
        try {
            this.unionX.add(Integer.parseInt(x));
            this.unionY.add(Integer.parseInt(y));
        } catch (NumberFormatException e) {
            System.out.println("Error: el valor recibido es del tipo incorrecto");

        }
    }

    void addTag(String tag) {
        this.etiquetas.add(tag);
    }

    void addValue(String val) {
        try {
            this.valores.add(Double.parseDouble(val));

        } catch (NumberFormatException e) {
            System.out.println("Error: el valor recibido es del tipo incorrecto");

        }
    }

    void setTotal(String val) {
        try {
            if (total == 0) {
                this.total = Integer.parseInt(val);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: el valor recibido es del tipo incorrecto");

        }

    }

    void setExtraName(String e) {
        if (this.extraName == null ||  this.extraName.isEmpty()) {
            this.extraName = e;
        } else {
            System.out.println("error, ua se habia definido el nombre del residuo");
        }
    }

    void create() {
        GraficaBarras graficaBarras = isBarrasValido();
        GraficaPie graficaPie = isPieValido();

        if (graficaBarras != null) {
            this.graficasDeBarras.add(graficaBarras);
            reset();
        } else if (graficaPie != null) {
            this.graficasDePie.add(graficaPie);
            reset();
        } else {
            //Falta añadir errores a una lista
            System.out.println("Error, no se han encontrado graficas validas");
            reset();
        }
    }

    private GraficaBarras isBarrasValido() {
        GraficaBarras graficaBarras = new GraficaBarras();

        if (barras && !pie) {
            if (cantidad || porcentaje) {
                System.out.println("Error, los tipos \"cantidad\" y \"porcentaje\" no pertenecen a la grafica de barras");
                return null;
            }
            if (total != 0) {
                System.out.println("Error, la grafica de barras no tiene un total");
                return null;

            }
            if (this.titulo != null && !this.titulo.isEmpty()) {
                graficaBarras.setTitulo(this.titulo);
                if (!this.elementosX.isEmpty() && !this.elementosY.isEmpty()) {
                    graficaBarras.setElementosX((ArrayList<String>) elementosX.clone());
                    graficaBarras.setElementosY((ArrayList<Double>) elementosY.clone());
                } else {
                    System.out.println("Error, debe declarar elementos en los dos ejes");
                    return null;

                }

                if (!unionX.isEmpty() && !unionY.isEmpty()) {
                    graficaBarras.setUnionX((ArrayList<Integer>) this.unionX.clone());
                    graficaBarras.setUnionY((ArrayList<Integer>) this.unionY.clone());
                } else {
                    System.out.println("Error, sin uniones");
                    return null;

                }
            }

        } else {
            System.out.println("error, la grafica esta mal definida");
            System.out.println(barras);
        }
        System.out.println(graficaBarras.toString());
        return graficaBarras;
    }

    private GraficaPie isPieValido() {
        GraficaPie graficaPie = new GraficaPie();
        if (pie && !barras) {
            graficaPie.setExtraName(extraName);

            if (titulo != null || !titulo.isEmpty()) {
                graficaPie.setTitulo(titulo);
            } else {
                System.out.println("Error, no se encontro un titulo");
                return null;
            }
            if (elementosX.isEmpty() && elementosY.isEmpty()) {
                if (porcentaje && total == 0) {
                    if (!unionX.isEmpty() && !unionY.isEmpty()) {
                        graficaPie.setUnionX(unionX);
                        graficaPie.setUnionY(unionY);
                    } else {
                        System.out.println("Error, sin uniones");
                        return null;
                    }
                    if (!etiquetas.isEmpty()) {
                        graficaPie.setEtiquetas(etiquetas);
                    } else {
                        System.out.println("Error, no hay etiquetas");
                        return null;
                    }
                    if (!valores.isEmpty()) {
                        graficaPie.setValores(valores);
                    } else {
                        System.out.println("Error, no hay valores");
                        return null;
                    }

                } else if (cantidad && total > 0) {
                    if (!unionX.isEmpty() && !unionY.isEmpty()) {
                        graficaPie.setUnionX(unionX);
                        graficaPie.setUnionY(unionY);
                    } else {
                        System.out.println("Error, sin uniones");
                        return null;
                    }
                    if (!etiquetas.isEmpty()) {
                        graficaPie.setEtiquetas(etiquetas);
                    } else {
                        System.out.println("Error, no hay etiquetas");
                        return null;
                    }
                    if (!valores.isEmpty()) {
                        graficaPie.setValores(valores);
                    } else {
                        System.out.println("Error, no hay valores");
                        return null;
                    }
                    graficaPie.setTotal(total);
                }
            } else {
                System.out.println("error, la grafica de pie no tiene ejes");
                return null;
            }

        } else {
            System.out.println("Error, grafica mal declarada");
            return null;
        }

        return graficaPie;
    }

    private void reset() {
        barras = false;
        pie = false;
        cantidad = false;
        porcentaje = false;
        titulo = "";
        extraName = "";
        total = 0;
        errores.clear();
        elementosX.clear();
        elementosY.clear();
        unionX.clear();
        unionY.clear();
        etiquetas.clear();
        valores.clear();
        warnings.clear();
    }


    /*
    def barras{
	titulo:"perro";
	ejex:["funciona","ya"];
	ejey:[5.5,6.5];
	unir:[{0,0},{1,1}];
}
def barras{
	titulo:"aaaa";
	ejex:["bbb","ccc"];
	ejey:[5.5,6.5];
	unir:[{0,0},{1,1}];
}
     */

    public ArrayList<GraficaBarras> getGraficasDeBarras() {
        return graficasDeBarras;
    }

    public ArrayList<GraficaPie> getGraficasDePie() {
        return graficasDePie;
    }

}
