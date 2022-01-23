package com.example.dogscloud.activities.Fragments.menu.trabajadores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.CuidadorEscogido
import com.example.dogscloud.activities.MyToolbar

class MisTrabajadores : AppCompatActivity() {

    var buttonGoTTrabajador: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_trabajadores)

        MyToolbar().show(this,"Trabajadores",true)

        buttonGoTTrabajador = findViewById(R.id.imageButton)

        buttonGoTTrabajador?.setOnClickListener{ goToTrabajador()}
    }

    private fun goToTrabajador(){
        val i = Intent(this, VerTrabajador::class.java)
        startActivity(i)
    }
}