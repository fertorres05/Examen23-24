package com.example.restaurante

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Reserva : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reserva)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val infoReserva: TextView = findViewById(R.id.infoReserva)
        val name: String = intent.extras?.getString("NOMBRE_CLIENTE").orEmpty()
        val asientos: String = intent.extras?.getString("ASIENTOS").orEmpty()
        val horas: String = intent.extras?.getString("HORA").orEmpty()

        infoReserva.text = "MESA RESERVADA PARA $asientos A NOMBRE DE $name, A LAS $horas:00"
    }
}