package com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class EditarMascotasCu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_mascotas_cu)

        MyToolbar().show(this,"Editar mi Mascota",true)
    }
}