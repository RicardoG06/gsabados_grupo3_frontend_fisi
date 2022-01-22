package com.example.dogscloud.activities.Fragments.menu.programacuidado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.PaseadorEscogido
import com.example.dogscloud.activities.MyToolbar

class PantallaCuidadorActivity : AppCompatActivity() {

    var buttonGoTCcuidador: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_cuidador)

        MyToolbar().show(this,"Cuidadores",true)

        buttonGoTCcuidador = findViewById(R.id.imageButton)

        buttonGoTCcuidador?.setOnClickListener{ goToCuidaodor()}
    }

    private fun goToCuidaodor(){
        val i = Intent(this, CuidadorEscogido::class.java)
        startActivity(i)
    }
}