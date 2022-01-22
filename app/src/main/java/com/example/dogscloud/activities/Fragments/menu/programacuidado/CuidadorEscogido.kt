package com.example.dogscloud.activities.Fragments.menu.programacuidado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.SolicitarCantidad
import com.example.dogscloud.activities.MyToolbar

class CuidadorEscogido : AppCompatActivity() {

    var buttonGoToSolicitarCantidad: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuidador_escogido)

        MyToolbar().show(this,"Cuidador",true)

        buttonGoToSolicitarCantidad = findViewById(R.id.button_go_to_solicitarcantidad)

        buttonGoToSolicitarCantidad?.setOnClickListener{ goToSolicitarCantidad() }
    }

    private fun goToSolicitarCantidad(){
        val i = Intent(this, SolicitarCantidadCu::class.java)
        startActivity(i)
    }
}