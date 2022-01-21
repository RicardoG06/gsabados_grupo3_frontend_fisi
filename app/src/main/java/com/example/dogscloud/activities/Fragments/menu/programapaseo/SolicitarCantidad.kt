package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SolicitarCantidad : AppCompatActivity() {

    var floatingGoToSeleccionarMascota: FloatingActionButton? = null
    var buttonGoToSolicitarPaseo: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_cantidad)

        MyToolbar().show(this,"Caracteristicas",true)

        floatingGoToSeleccionarMascota = findViewById(R.id.floating_go_to_seleccionarmascota)
        buttonGoToSolicitarPaseo = findViewById(R.id.button_go_to_solicitarpaseo)

        floatingGoToSeleccionarMascota?.setOnClickListener{ goToSeleccionarMascota() }
        buttonGoToSolicitarPaseo?.setOnClickListener{ goToSolicitarPaseo() }

    }

    private fun goToSeleccionarMascota(){
        val i = Intent(this, SeleccionarMascota::class.java)
        startActivity(i)
    }

    private fun goToSolicitarPaseo(){
        val i = Intent(this, SolicitarPaseo::class.java)
        startActivity(i)
    }
}