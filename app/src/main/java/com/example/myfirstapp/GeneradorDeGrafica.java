/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.myfirstapp;

import java.io.Serializable;
import java.util.ArrayList;
import java_cup.runtime.Symbol;

/**
 *
 * @author ordson
 */
public class GeneradorDeGrafica implements Serializable {
    boolean ejecutar = false;
    boolean barras = false;
    boolean pie = false;
    boolean cantidad = false;
    boolean porcentaje = false;
    String titulo;
    String extraName;
    int total;
    ArrayList<Mistake> errores = new ArrayList<>();
    ArrayList<String> elementosX = new ArrayList<>();
    ArrayList<Double> elementosY = new ArrayList<>();
    ArrayList<Integer> unionX = new ArrayList<>();
    ArrayList<Integer> unionY = new ArrayList<>();
    ArrayList<String> etiquetas = new ArrayList<>();
    ArrayList<Double> valores = new ArrayList<>();
    ArrayList<String> warnings = new ArrayList<>();
    ArrayList<String> Repetidas = new ArrayList<>();
    ArrayList<GraficaBarras> barrasEjecucion = new ArrayList<>();
    ArrayList<GraficaPie> pieEjecucion = new ArrayList<>();
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
        if (this.extraName == null || this.extraName.isEmpty()) {
            this.extraName = e;
        } else {
            System.out.println("error, ua se habia definido el nombre del residuo");
        }
    }

    void create() {

        if (barras) {
            GraficaBarras graficaBarras = isBarrasValida();
            if (graficaBarras != null) {
                graficasDeBarras.add(graficaBarras);
                reset();
                return;
            }

        } else if (pie) {
            GraficaPie pie = isPieValido();
            if (pie != null) {
                graficasDePie.add(pie);
                reset();
                return;
            }
        }
        reset();
        warnings.add("No se encontraron graficas validas");

    }

    private GraficaBarras isBarrasValida() {
        GraficaBarras graficaBarras = new GraficaBarras();

        if (barras && !pie) {
            if (cantidad) {
                System.out.println("Error, los tipos \"cantidad\" y \"porcentaje\" no pertenecen a la grafica de barras");
                errores.add(new Mistake("El atributo \"cantidad\" no pertenece a la grafica de barras", "cantidad"));
                return null;
            }
            if (porcentaje) {
                errores.add(new Mistake("El atributo \"porcentaje\" no pertenece a la grafica de barras", "porcentaje"));
            }
            if (total != 0) {
                System.out.println("Error, la grafica de barras no tiene un total");
                errores.add(new Mistake("El atributo \"total\" no pertenece a la grafica de barras", "total"));

                return null;

            }
            if (this.titulo != null && !this.titulo.isEmpty()) {
                graficaBarras.setTitulo(this.titulo);
                if (!this.elementosX.isEmpty() && !this.elementosY.isEmpty()) {
                    graficaBarras.setElementosX((ArrayList<String>) elementosX.clone());
                    graficaBarras.setElementosY((ArrayList<Double>) elementosY.clone());
                } else {
                    errores.add(new Mistake("Ambos ejes deben contener elementos", "Ejes"));
                    return null;

                }

                if (!unionX.isEmpty() && !unionY.isEmpty()) {
                    graficaBarras.setUnionX((ArrayList<Integer>) this.unionX.clone());
                    graficaBarras.setUnionY((ArrayList<Integer>) this.unionY.clone());
                } else {
                    errores.add(new Mistake("No se encontraron datos de union", "unir"));
                    return null;

                }
            } else {
                errores.add(new Mistake("La grafica debe tener un titulo", "titulo"));
                return null;
            }

        } else {
            errores.add(new Mistake("Error en definicion de tipo", ""));

            System.out.println(barras);
            return null;
        }
        System.out.println(graficaBarras.toString());
        if (buscarRepeticion(graficaBarras.getTitulo()) && errores.isEmpty()) {
            return graficaBarras;
        }
        return null;
    }

    private GraficaPie isPieValido() {
        GraficaPie graficaPie = new GraficaPie();
        if (pie && !barras) {
            graficaPie.setExtraName(extraName);

            if (titulo != null  || !titulo.isEmpty()) {
                graficaPie.setTitulo(titulo);
            } else {
                System.out.println("Error, no se encontro un titulo");
                errores.add(new Mistake("La grafica debe tener un titulo", "titulo"));

                return null;
            }
            if (elementosX.isEmpty() && elementosY.isEmpty()) {
                if (porcentaje && total == 0) {
                    graficaPie.setTotal(100);
                    if (!unionX.isEmpty() && !unionY.isEmpty()) {
                        graficaPie.setUnionX((ArrayList<Integer>) unionX.clone());
                        graficaPie.setUnionY((ArrayList<Integer>) unionY.clone());
                    } else {
                        System.out.println("Error, sin uniones");
                        errores.add(new Mistake("No se encontraron datos de union", "unir"));

                        return null;
                    }
                    if (!etiquetas.isEmpty()) {
                        graficaPie.setEtiquetas((ArrayList<String>) etiquetas.clone());
                    } else {
                        System.out.println("Error, no hay etiquetas");
                        errores.add(new Mistake("No se encontraron etiquetas", "etiquetas"));

                        return null;
                    }
                    if (!valores.isEmpty()) {
                        graficaPie.setValores((ArrayList<Double>) valores.clone());
                    } else {
                        System.out.println("Error, no hay valores");
                        errores.add(new Mistake("No se encontraron valores", "valores"));

                        return null;
                    }

                } else if (cantidad && total > 0) {
                    if (!unionX.isEmpty() && !unionY.isEmpty()) {
                        graficaPie.setUnionX((ArrayList<Integer>) unionX.clone());
                        graficaPie.setUnionY((ArrayList<Integer>) unionY.clone());
                    } else {
                        System.out.println("Error, sin uniones");
                        errores.add(new Mistake("No se encontraron datos de union", "unir"));

                        return null;
                    }
                    if (!etiquetas.isEmpty()) {
                        graficaPie.setEtiquetas((ArrayList<String>) etiquetas.clone());
                    } else {
                        System.out.println("Error, no hay etiquetas");
                        errores.add(new Mistake("No se encontraron etiquetas", "etiquetas"));

                        return null;
                    }
                    if (!valores.isEmpty()) {
                        graficaPie.setValores((ArrayList<Double>) valores.clone());
                    } else {
                        System.out.println("Error, no hay valores");
                        errores.add(new Mistake("No se encontraron valores", "valores"));

                        return null;
                    }
                    graficaPie.setTotal(total);
                }
            } else {
                System.out.println("error, la grafica de pie no tiene ejes");
                errores.add(new Mistake("La grafica de pie no tiene ejes", ""));

                return null;
            }

        } else {
            System.out.println("Error, grafica mal declarada");
            errores.add(new Mistake("Error en declaracion de grafica", ""));

            return null;
        }

        if (buscarRepeticion(graficaPie.getTitulo()) && errores.isEmpty()) {
            return graficaPie;
        }
        return null;
    }

    private boolean buscarRepeticion(String titulo) {
        for (GraficaBarras temp : graficasDeBarras) {
            if (temp.getTitulo().equals(titulo)) {
                Repetidas.add(titulo);
                return false;
            }
        }
        for (GraficaPie temp : graficasDePie) {
            if (temp.getTitulo().equals(titulo)) {
                Repetidas.add(titulo);
                return false;
            }
        }
        return true;

    }

    private void reset() {
        barras = false;
        pie = false;
        cantidad = false;
        porcentaje = false;
        titulo = "";
        titulo = null;
        titulo = new String();
        extraName = "";
        extraName = null;
        titulo = new String();
        total = 0;
        elementosX.clear();
        elementosY.clear();
        unionX.clear();
        unionY.clear();
        etiquetas.clear();
        valores.clear();
        warnings.clear();
    }

    public void setGraficasDeBarras(ArrayList<GraficaBarras> barras){
        this.graficasDeBarras = barras;
    }
    public void setGraficasDePie(ArrayList<GraficaPie> pies){
        this.graficasDePie = pies;
    }
    /*
    def Barras{
                titulo: "Grafica1";
                ejex:["item1", "item2"];
                ejey:[5.0, 9.0];
                unir:[{0,1}, {1,0}];
}

Def Pie{
titulo: "Grafica2";
tipo: Cantidad;
etiquetas: ["Compi1", "Compi2"];
valores:[5, 1];
total: 25;
unir:[{0,1}, {1,0}];
extra: "Resto";
}
Def Pie{
titulo: "Grafica3";
tipo: Porcentaje;
etiquetas: ["Compi1", "Compi2"];
valores:[7, 12];
unir:[{0,1}, {1,0}];
extra: "Resto";
}

Ejecutar("Grafica1");
Ejecutar("Grafica2");
Ejecutar("Grafica3");


     */
    public ArrayList<GraficaBarras> getGraficasDeBarras() {
        return graficasDeBarras;
    }

    public ArrayList<GraficaPie> getGraficasDePie() {
        return graficasDePie;
    }

    public void addMistake(Mistake mistake) {
        this.errores.add(mistake);
        System.out.println(mistake.toString());
    }

    void addEjecucion(String nombre) {
        for (GraficaBarras temp : graficasDeBarras) {
            if (temp.getTitulo().equals(nombre)) {
                barrasEjecucion.add(temp);
                ejecutar = true;
            }
        }
        for (GraficaPie temp:graficasDePie) {
            if (temp.getTitulo().equals(nombre)) {
                pieEjecucion.add(temp);
                ejecutar = true;
            }
        }
        
        if (!errores.isEmpty()) {
            ejecutar = false;
            barrasEjecucion.clear();
            pieEjecucion.clear();
        }
        
    }

    public ArrayList<GraficaBarras> getBarrasEjecucion() {
        return barrasEjecucion;
    }

    public ArrayList<GraficaPie> getPieEjecucion() {
        return pieEjecucion;
    }



}
