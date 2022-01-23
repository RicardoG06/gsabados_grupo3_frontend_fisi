package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu.MisMascotasCu
import com.example.dogscloud.activities.Fragments.menu.programapaseo.MascotasPa.MisMascotasPa
import com.example.dogscloud.activities.MyToolbar

class SeleccionarMascota : AppCompatActivity() {
    var buttonGoToSolicitarCantidad: Button? = null
    var AgregarMascotas: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_mascota)


        MyToolbar().show(this,"Seleccionar Mascota",true)

        buttonGoToSolicitarCantidad = findViewById(R.id.button_go_to_solicitarcantidad)
        AgregarMascotas = findViewById(R.id.AgregarMascotas)

        buttonGoToSolicitarCantidad?.setOnClickListener{ goToSolicitarCantidad() }
        AgregarMascotas?.setOnClickListener{ goToAgregarMascotas() }

    }

    private fun goToSolicitarCantidad(){
        val i = Intent(this, SolicitarCantidad::class.java)
        startActivity(i)
    }

    private fun goToAgregarMascotas(){
        val i = Intent(this, MisMascotasPa::class.java)
        startActivity(i)
    }
}