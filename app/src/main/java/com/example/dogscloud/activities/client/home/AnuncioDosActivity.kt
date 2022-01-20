package com.example.dogscloud.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.dogscloud.R

class AnuncioDosActivity : AppCompatActivity() {

    var goToReco1: Button? = null
    var goToReco3: Button? = null
    var goToAnuncio: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anuncio_dos)

        goToReco1 = findViewById(R.id.btn_recomendado2TumascotaSegura1)

        goToReco1?.setOnClickListener {
            goToReco1()
        }

        goToReco3 = findViewById(R.id.btn_recomendado2TumascotaSegura3)

        goToReco3?.setOnClickListener {
            goToReco3()
        }

        goToAnuncio = findViewById(R.id.btn_saltar2)

        goToAnuncio?.setOnClickListener {
            goToAnuncio()
        }
    }

    private fun goToReco1() {
        val i = Intent(this, AnuncioUnoActivity::class.java)
        startActivity(i)
    }

    private fun goToReco3() {
        val i = Intent(this, AnuncioTresActivity::class.java)
        startActivity(i)
    }

    private fun goToAnuncio() {
        val i = Intent(this, SugerenciaActivity::class.java)
        startActivity(i)
    }
}
