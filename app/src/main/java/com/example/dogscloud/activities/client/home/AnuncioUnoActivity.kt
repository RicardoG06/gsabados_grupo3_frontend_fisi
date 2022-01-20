package com.example.dogscloud.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.dogscloud.R
import com.example.dogscloud.activities.SignUpActivity
import org.w3c.dom.Text

class AnuncioUnoActivity : AppCompatActivity() {

    var goToReco2: Button? = null
    var goToReco3: Button? = null
    var goToAnuncio: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anuncio_uno)

        goToReco2 = findViewById(R.id.btn_recomendado1TumascotaSegura2)

        goToReco2?.setOnClickListener{
            goToReco2()
        }

        goToReco3 = findViewById(R.id.btn_recomendado1TumascotaSegura3)

        goToReco3?.setOnClickListener{
            goToReco3()
        }

        goToAnuncio = findViewById(R.id.btn_saltar1)

        goToAnuncio?.setOnClickListener {
            goToAnuncio()
        }
    }


    private fun goToReco2() {
        val i = Intent(this, AnuncioDosActivity::class.java)
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