package com.example.myfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import java.io.StringReader
import java.util.ArrayList
import kotlin.math.log

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun sendMessage(view: View){
        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val texto:String = editText.text.toString()



        val pepe:pepito = pepito()


        val escupeNumeros = pepe.escupeLetras()
        val intent = Intent(this,ActividadMuestraMensaje::class.java).apply{
            putExtra(EXTRA_MESSAGE,texto)
        }
        startActivity(intent)
    }

}