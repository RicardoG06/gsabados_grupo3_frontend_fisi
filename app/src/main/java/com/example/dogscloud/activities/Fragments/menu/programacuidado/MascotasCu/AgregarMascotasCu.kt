package com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class AgregarMascotasCu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mascotas_cu)

        MyToolbar().show(this,"Agregar una mascota",true)
    }
}