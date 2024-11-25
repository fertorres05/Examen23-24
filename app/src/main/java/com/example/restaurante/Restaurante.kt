package com.example.restaurante

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class Restaurante : AppCompatActivity() {

    //Definimos los objetos

    //Mesas
    private lateinit var view2Asientos:CardView
    private lateinit var view4Asientos:CardView
    private lateinit var view8Asientos:CardView

    //Nombre Cliente
    private lateinit var inputNombre:EditText

    //Hora de reserva
    private lateinit var tvHora:TextView
    private lateinit var adelantarHora:FloatingActionButton
    private lateinit var atrasarHora:FloatingActionButton

    //Boton reserva

    private  lateinit var botonReserva:FloatingActionButton

    var is2asientosSelected:Boolean=true
    var is4asientosSelected:Boolean=false
    var is8asientosSelected:Boolean=false

    //variable hora
    private var hora:Int=20
    private var asientos:Int=2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_restaurante)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initComponents()
        initListeners()
        initUI()

    }

    //Iniciamos los componentes
    private fun initComponents(){

        //Mesas
        view2Asientos=findViewById(R.id.asientos2)
        view4Asientos=findViewById(R.id.asientos4)
        view8Asientos=findViewById(R.id.asientos8)

        //Nombre Cliente
        inputNombre=findViewById(R.id.inNom)

        //Hora de reserva
        tvHora=findViewById(R.id.tvHora)
        adelantarHora=findViewById(R.id.adelantarHora)
        atrasarHora=findViewById(R.id.atrasarHora)

        //Boton reserva
        botonReserva=findViewById(R.id.reservar)
    }

    fun initListeners(){
        //Asientos
        view2Asientos.setOnClickListener(){
            is2asientosSelected=true
            is4asientosSelected=false
            is8asientosSelected=false
            setAsientosColor()
            asientos=2

        }
        view4Asientos.setOnClickListener(){
            is2asientosSelected=false
            is4asientosSelected=true
            is8asientosSelected=false
            setAsientosColor()
            asientos=4
        }
        view8Asientos.setOnClickListener(){
            is2asientosSelected=false
            is4asientosSelected=false
            is8asientosSelected=true
            setAsientosColor()
            asientos=8
        }
        //Boton horas
        adelantarHora.setOnClickListener(){
            if (hora<22){
                hora++
            }else{
                hora=20
            }
            setHora()
        }
        atrasarHora.setOnClickListener(){
            if (hora>20){
                hora--
            }else{
                hora=22
            }
            setHora()
        }
        //Boton reserva
        botonReserva.setOnClickListener {
            val name = inputNombre.text.toString()
            val intHora= hora.toString()
            val intAsientos=asientos.toString()
            val intentGA = Intent(this, Reserva::class.java)
            intentGA.putExtra("NOMBRE_CLIENTE", name)
            intentGA.putExtra("ASIENTOS", intAsientos)
            intentGA.putExtra("HORA", intHora)
            startActivity(intentGA)

        }
    }

    fun setAsientosColor(){
        if (is2asientosSelected){
            view2Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.elementos_seleccionados))
            view4Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fondo_elemetos))
            view8Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fondo_elemetos))
        }else if (is4asientosSelected) {
            view2Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fondo_elemetos))
            view4Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.elementos_seleccionados))
            view8Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fondo_elemetos))
        }else{
            view2Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fondo_elemetos))
            view4Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.fondo_elemetos))
            view8Asientos.setCardBackgroundColor(ContextCompat.getColor(this, R.color.elementos_seleccionados))
            }
        }

    fun setHora(){
        tvHora.text=hora.toString()+":00"
    }

    fun initUI(){
        setAsientosColor()
        setHora()
    }
}