package com.example.myfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class Reportes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportes)
        supportActionBar?.title="Errores"
        val generador = intent.getSerializableExtra("Generador") as GeneradorDeGrafica
        val mistakes = generador.errores

        if (mistakes.isNotEmpty()){
            escribirErrores(generador)
        }

    }
    fun escribirErrores(generadorDeGrafica: GeneradorDeGrafica){
        val txtLinea = findViewById<TextView>(R.id.txtLinea)
        val txtColumna = findViewById<TextView>(R.id.txtColumna)
        val txtTipo = findViewById<TextView>(R.id.txtTipo)
        val txtDescipcion = findViewById<TextView>(R.id.txtDescripcion)
        val txtLexema = findViewById<TextView>(R.id.txtLexema)

        val nuevaLinea = StringBuilder()
        val nuevaColumna = StringBuilder()
        val nuevoTipo = StringBuilder()
        val nuevaDescripcion = StringBuilder()
        val nuevoLexema = StringBuilder()

        for (temp in generadorDeGrafica.errores){
            nuevaLinea.appendLine(temp.linea+"\n")
            nuevaColumna.appendLine(temp.columna+"\n")
            nuevoTipo.appendLine(temp.tipo+"\n")
            nuevaDescripcion.appendLine(temp.descripcion+"\n")
            nuevoLexema.appendLine(temp.lexema+"\n")
            Log.e("error",temp.toString())
        }
        txtLinea.setText(nuevaLinea)
        txtColumna.setText(nuevaColumna)
        txtLexema.setText(nuevoLexema)
        txtTipo.setText(nuevoTipo)
        txtDescipcion.setText(nuevaDescripcion)
    }
}