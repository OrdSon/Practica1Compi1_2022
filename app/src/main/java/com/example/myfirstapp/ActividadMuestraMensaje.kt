package com.example.myfirstapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import java.io.StringReader
import kotlin.collections.ArrayList

class ActividadMuestraMensaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title="Graficas"
        setContentView(R.layout.activity_actividad_muestra_mensaje)
        val generador = intent.getSerializableExtra("Generador") as GeneradorDeGrafica
        val barras = generador.getBarrasEjecucion()
        val pies = generador.getPieEjecucion()
        val layout1 = findViewById<LinearLayout>(R.id.Layout1)
        for (temp in barras){
            Log.i("MAS ALLA DEL RIO",temp.toString())
            val barras = BarChart(this)
            generarGraficaBarras(barras,temp,layout1)
        }
        for (temp in pies){
            val pie = PieChart(this)
            generarGraficaPie(pie,temp,layout1)
            Log.i("MAS ALLA DEL RIO",temp.toString())
        }
    }
/*
* //Aqui se recibe el texto de la otra pantalla y se le aplica al label
        val textView = findViewById<TextView>(R.id.textView).apply{
            text = message;
        }
        //Aqui se usa findViewById para convertir el elemento grafico en un objeto

        val barras = findViewById<BarChart>(R.id.BarChart)
        generarGrafica(message+"", barras)
* */

    fun generarGraficaPie(pieChart: PieChart, temp:GraficaPie, layout1: LinearLayout){
        val entradas = ArrayList<PieEntry>()
        val total = temp.total
        val valores = temp.valores
        val etiquetas = temp.etiquetas
        val extraname = temp.extraName
        val titulo = temp.titulo
        val unirx=temp.unionX
        val uniry=temp.unionY
        var resultado:Double = 0.0;
        for(sub in valores){
            resultado+=sub;
        }
        if (resultado>total){
            Log.e("Error",resultado.toString())
            return
        }
        for (i in 0 until unirx.size){
            if (unirx.get(i)>=valores.size || uniry.get(i)>=etiquetas.size){
                return
            }else{
                entradas.add(PieEntry(valores.get(unirx.get(i)).toFloat(),etiquetas.get(uniry.get(i))))
            }
        }

        val residuo = total - resultado
        if (residuo > 0){
            entradas.add(PieEntry(residuo.toFloat(),extraname))
        }

        val pieDataSet = PieDataSet(entradas,titulo)
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS,255)
        pieDataSet.setValueTextColor(Color.BLACK)
        pieDataSet.valueTextSize = 16.toFloat()
        pieChart.description.setEnabled(false)
        pieChart.setEntryLabelTextSize(16.toFloat())
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.legend.textSize = 16.toFloat()


        val pieData = PieData(pieDataSet)

        pieChart.setData(pieData)
        pieChart.setCenterText(titulo)
        pieChart.animate()

        layout1.addView(pieChart)
        pieChart.getLayoutParams().height = 600;
        pieChart.getLayoutParams().width = 900;
    }
    fun generarGraficaBarras(barChart: BarChart, temp:GraficaBarras, layout1:LinearLayout) {

        /*
        * El grafico necesita algunas cosas para inicializarse:
        * Posicion en el ejeX (posicion, no "etiquetas")
        * Posicion en el ejeY
        * Y etiquetas
        * */

        //Aqui se inicializa el parser
        /*El parser devuelve un array de graficas de barras que tienen 4 listas
        etiquetas ejex
        ejey
        unionX
        unionY
        * */
            //Se obtienen las listas de cada grafica para agregarlas a otras listas
                //las listas nuevas se usan para graficar
            val unirX: ArrayList<Int> = temp.unionX
            val unirY = temp.unionY
            val elementosX = temp.elementosX
            val elementosY = temp.elementosY
            val ejey = ArrayList<Double>()
            val ejex = ArrayList<String>()
            barChart.getDescription().setText(temp.titulo)
            barChart.description.textSize = 18.toFloat()

            for (i in 0 until unirX.size){
                //Se asignan los elementos del conjunto X al conjunto Y usando las instrucciones de
                    //Unir:[{ }]
                        if (unirX.get(i)<elementosX.size &&  unirX.get(i)>=0){
                            ejex.add(elementosX.get(unirX.get(i)))
                        }
                Log.i("error","EL TAMAÑO DE UNIR ES:"+unirX.size)
            }
            for (i in 0 until ejex.size){
                //Se asignan los elementos del conjunto X al conjunto Y usando las instrucciones de
                //Unir:[{ }]
                    if (unirY.get(i)< elementosY.size && unirY.get(i)>=0){
                        ejey.add(elementosY.get(unirY.get(i)))
                    }
            }
            Log.e("error","EL TAMAÑO DE EJEX ES: "+ejex.size)
            var entradas = ArrayList<BarEntry>()
            var leyendas = ArrayList<LegendEntry>()
            for (i in 0 until ejex.size){
                /*Aqui se usan las listas nuevas para añadir entradas a la grafica
                tambien se usan las "Leyendas" para mostrar las etiquetas
                * */

                val temp = LegendEntry()
                temp.label = ejex.get(i);
                leyendas.add(temp)
            }
            for(i in 0 until ejey.size){
                entradas.add(BarEntry(i.toFloat(),ejey.get(i).toFloat()))
            }
            //Se ajusta el aspecto de las leyendas, es opcional, hace que se vean abajo
            val legend = barChart.legend
            legend.form = Legend.LegendForm.CIRCLE
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.setCustom(leyendas)
            legend.textSize = 16.toFloat()

        //Se crea un bar data set para el grafico
            val barDataSet = BarDataSet(entradas,"")
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,200)
            barDataSet.setValueTextColor(Color.BLACK)
            barDataSet.valueTextSize = 18.toFloat()

            val barData = BarData(barDataSet)
            barChart.setFitBars(true)
            barChart.setData(barData)
            //Aqui deberia ir el titulo pero ya que
            barChart.legend.textSize = 16.toFloat()
            barChart.axisLeft.textSize = 12.toFloat()
            barChart.xAxis.valueFormatter=(IndexAxisValueFormatter(ejex))
            barChart.xAxis.textSize=16.toFloat()

        barChart.animateY(1500)
             legend.isEnabled = false


            //Se ajusta el tamaño de la cuadricula en el eje x para que no muestre
            //numeros decimales

            barChart.xAxis.granularity = 1.toFloat()
            layout1.addView(barChart)
            barChart.getLayoutParams().height = 600;
            barChart.getLayoutParams().width = 900;
    }



}
