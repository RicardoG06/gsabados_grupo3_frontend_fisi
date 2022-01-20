package com.example.dogscloud.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.dogscloud.R

class AnuncioTresActivity : AppCompatActivity() {

    var goToReco1: Button? = null
    var goToReco2: Button? = null
    var goToAnuncio1: TextView? = null
    var goToAnuncio2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anuncio_tres)

        goToReco1 = findViewById(R.id.btn_recomendado3TumascotaSegura1)

        goToReco1?.setOnClickListener {
            goToReco1()
        }

        goToReco2 = findViewById(R.id.btn_recomendado3TumascotaSegura2)

        goToReco2?.setOnClickListener {
            goToReco2()
        }

        goToAnuncio2 = findViewById(R.id.btn_comencemos)

        goToAnuncio2?.setOnClickListener {
            goToAnuncio() }

        goToAnuncio1 = findViewById(R.id.btn_saltar3)

        goToAnuncio1?.setOnClickListener {
            goToAnuncio()
        }



    }

    private fun goToReco1() {
        val i = Intent(this, AnuncioUnoActivity::class.java)
        startActivity(i)
    }

    private fun goToReco2() {
        val i = Intent(this, AnuncioDosActivity::class.java)
        startActivity(i)
    }

    private fun goToAnuncio() {
        val i = Intent(this, SugerenciaActivity::class.java)
        startActivity(i)
    }
}