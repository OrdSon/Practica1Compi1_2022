package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import java.io.Serializable
import java.io.StringReader
import java.lang.StringBuilder
import java.util.ArrayList
import kotlin.math.log

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

class MainActivity : AppCompatActivity() {
     var generador = GeneradorDeGrafica()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.title ="Graficadora"
        showGraphicsTitle()

    }
    fun analizar(view:View){
        limpiar()
        val editText = findViewById<EditText>(R.id.editTextTextMultiLine)
        val texto:String = editText.text.toString()
        val lector: StringReader = StringReader(texto)
        val analizador:Lexer = Lexer(lector)
        analizador.setGenerador(this.generador)
        val sintactico:Parser = Parser(analizador);
        sintactico.setGenerador(this.generador)
        sintactico.parse()
        showGraphicsTitle()
        contar()
        ejecutar()
    }
    fun showGraphicsTitle(){
        val textField =  findViewById<TextView>(R.id.textField)
        val barras = generador.graficasDeBarras
        val pies = generador.graficasDePie
        val builtText = StringBuilder();
        for (temp in barras){
            builtText.appendLine("Grafica de barras: "+temp.titulo)
        }
        for (temp in pies){
            builtText.appendLine("Grafica de pie: "+temp.titulo)
        }

        textField.setText(builtText)
    }

    fun ejecutar(){
        if (generador.ejecutar){
            val barras = generador.getBarrasEjecucion()
            val pies = generador.getPieEjecucion()
            for(temp in barras){
                Log.i("grafica",temp.toString())
            }
            for(temp in pies){
                Log.i("grafica",temp.toString())
            }
            sendGenerador()
        }
    }
    fun limpiar(){
        generador.errores.clear()
        generador.barrasEjecucion.clear()
        generador.pieEjecucion.clear()
    }
    fun sendMessage(view: View){
        val editText = findViewById<EditText>(R.id.editTextTextMultiLine)
        val texto:String = editText.text.toString()



        val pepe:pepito = pepito()


        val escupeNumeros = pepe.escupeLetras()
        val intent = Intent(this,ActividadMuestraMensaje::class.java).apply{
            putExtra(EXTRA_MESSAGE,texto)
        }
        startActivity(intent)
    }
    fun contar(){

        if (generador.errores.isEmpty()){
            val textV = findViewById<TextView>(R.id.textView2)
            val builder = StringBuilder()
            builder.append("Graficas analizadas:   ")
            builder.append("De barras: "+generador.graficasDeBarras.size+"   De pie: "+generador.graficasDePie.size)
            textV.setText(builder)
        }
    }
    fun sendGenerador(){

        val intent = Intent(this,ActividadMuestraMensaje::class.java).apply{
            putExtra("Generador",generador)
        }
        startActivity(intent)
    }
    fun verRerporte(view:View){
        if (!generador.errores.isEmpty()){
            val intent = Intent(this,Reportes::class.java).apply{
                putExtra("Generador",generador)
            }
            startActivity(intent)
        }
    }
}


