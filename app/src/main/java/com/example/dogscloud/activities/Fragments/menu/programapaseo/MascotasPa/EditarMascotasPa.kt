package com.example.dogscloud.activities.Fragments.menu.programapaseo.MascotasPa

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class EditarMascotasPa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mascotas_pa)

        MyToolbar().show(this,"Editar mi Mascota",true)
    }
}