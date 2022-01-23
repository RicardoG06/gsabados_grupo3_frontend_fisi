package com.example.dogscloud.activities.Fragments.menu.programapaseo.MascotasPa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class AgregarMascotasPa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mascotas_pa)

        MyToolbar().show(this,"Agregar una mascota",true)
    }
}