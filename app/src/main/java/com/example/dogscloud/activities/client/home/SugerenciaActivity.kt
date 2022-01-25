package com.example.dogscloud.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.dogscloud.R

class SugerenciaActivity : AppCompatActivity() {

    private lateinit var imageViewGoToHome: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sugerencia)

        imageViewGoToHome = findViewById(R.id.imageview_go_to_home)
        imageViewGoToHome.setOnClickListener {
            goToHome()
        }

    }

    private fun goToHome() {
        val i = Intent(this, MainActivity:: class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK //Eliminar historia de pantalla
        startActivity(i)
    }
}