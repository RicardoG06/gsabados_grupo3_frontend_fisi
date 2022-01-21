package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class PaseadorEscogido : AppCompatActivity() {
    var buttonGoToSolicitarCantidad: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_paseador_escogido)

        MyToolbar().show(this,"Paseador",true)

        buttonGoToSolicitarCantidad = findViewById(R.id.button_go_to_solicitarcantidad)

        buttonGoToSolicitarCantidad?.setOnClickListener{ goToSolicitarCantidad() }

    }

    private fun goToSolicitarCantidad(){
        val i = Intent(this, SolicitarCantidad::class.java)
        startActivity(i)
    }
}