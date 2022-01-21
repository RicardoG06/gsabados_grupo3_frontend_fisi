package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class SeleccionarMascota : AppCompatActivity() {
    var buttonGoToSolicitarCantidad: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_mascota)

        MyToolbar().show(this,"Seleccionar Mascota",true)

        buttonGoToSolicitarCantidad = findViewById(R.id.button_go_to_solicitarcantidad)

        buttonGoToSolicitarCantidad?.setOnClickListener{ goToSolicitarCantidad() }

    }

    private fun goToSolicitarCantidad(){
        val i = Intent(this, SolicitarCantidad::class.java)
        startActivity(i)
    }
}