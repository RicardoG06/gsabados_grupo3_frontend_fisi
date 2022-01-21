package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class PantallaPaseadorActivity : AppCompatActivity() {

    var buttonGoToPaseador: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_paseador)

        MyToolbar().show(this,"Paseadores",true)

        buttonGoToPaseador = findViewById(R.id.imageButton)

        buttonGoToPaseador?.setOnClickListener{ goToPaseador()}

    }

    private fun goToPaseador(){
        val i = Intent(this, PaseadorEscogido::class.java)
        startActivity(i)
    }
}