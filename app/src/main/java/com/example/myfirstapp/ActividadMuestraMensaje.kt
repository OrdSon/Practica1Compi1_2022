package com.example.myfirstapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.LegendEntry
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.io.StringReader
import kotlin.collections.ArrayList

class ActividadMuestraMensaje : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_muestra_mensaje)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        //Aqui se recibe el texto de la otra pantalla y se le aplica al label
        val textView = findViewById<TextView>(R.id.textView).apply{
            text = message;
        }
        //Aqui se usa findViewById para convertir el elemento grafico en un objeto

        val barras = findViewById<BarChart>(R.id.BarChart)
        generarGrafica(message+"", barras)
    }

    fun generarGrafica(texto:String, barChart: BarChart) {

        /*
        * El grafico necesita algunas cosas para inicializarse:
        * Posicion en el ejeX (posicion, no "etiquetas")
        * Posicion en el ejeY
        * Y etiquetas
        * */

        //Aqui se inicializa el parser
        val lector: StringReader = StringReader(texto)
        val analizador:Lexer = Lexer(lector)
        val sintactico:Parser = Parser(analizador);
        sintactico.parse();
        val generador:GeneradorDeGrafica = sintactico.getGenerador();
        /*El parser devuelve un array de graficas de barras que tienen 4 listas
        etiquetas ejex
        ejey
        unionX
        unionY
        * */
        val barras = generador.graficasDeBarras;

        for (temp in barras){
            //Se obtienen las listas de cada grafica para agregarlas a otras listas
                //las listas nuevas se usan para graficar
            val unirX: ArrayList<Int> = temp.unionX
            val unirY = temp.unionY
            val elementosX = temp.elementosX
            val elementosY = temp.elementosY
            val ejey = ArrayList<Double>()
            val ejex = ArrayList<String>()
            for (i in 0 until unirX.size){
                //Se asignan los elementos del conjunto X al conjunto Y usando las instrucciones de
                    //Unir:[{ }]
                ejey.add(elementosY.get(unirY.get(i)))
                ejex.add(elementosX.get(unirX.get(i)))
            }
            var entradas = ArrayList<BarEntry>()
            var leyendas = ArrayList<LegendEntry>()
            for (i in 0 until ejex.size){
                /*Aqui se usan las listas nuevas para añadir entradas a la grafica
                tambien se usan las "Leyendas" para mostrar las etiquetas
                * */
                entradas.add(BarEntry(i.toFloat(),elementosY.get(i).toFloat(),elementosX.get(i)))
                val temp = LegendEntry()
                temp.formColor = ColorTemplate.MATERIAL_COLORS[i]
                temp.label = elementosX.get(i);
                leyendas.add(temp)
            }

            //Se ajusta el aspecto de las leyendas, es opcional, hace que se vean abajo
            val legend = barChart.legend
            legend.form = Legend.LegendForm.CIRCLE
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.setCustom(leyendas)

        //Se crea un bar data set para el grafico
            val barDataSet = BarDataSet(entradas,"")
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,200)
            barDataSet.setValueTextColor(Color.BLACK)

            val barData = BarData(barDataSet)
            barChart.setFitBars(true)
            barChart.setData(barData)
            //Aqui deberia ir el titulo pero ya que
            barChart.getDescription().setText("Barras")
            barChart.animateY(2000)

            //Se ajusta el tamaño de la cuadricula en el eje x para que no muestre
            //numeros decimales
            barChart.xAxis.granularity = 1.toFloat()

        }
    }



}
