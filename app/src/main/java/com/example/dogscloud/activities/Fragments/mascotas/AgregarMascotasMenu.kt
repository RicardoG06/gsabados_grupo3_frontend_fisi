package com.example.dogscloud.activities.Fragments.mascotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class AgregarMascotasMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mascotas_menu)

        MyToolbar().show(this,"Agregar una mascota",true)
    }
}