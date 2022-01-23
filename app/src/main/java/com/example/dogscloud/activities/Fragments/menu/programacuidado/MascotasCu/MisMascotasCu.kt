package com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.mascotas.AgregarMascotasMenu
import com.example.dogscloud.activities.Fragments.mascotas.EditarMascotasMenu
import com.example.dogscloud.activities.MyToolbar

class MisMascotasCu : AppCompatActivity() {

    var btn_editarmascota: Button? = null
    var btn_agregarmascota: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_mascotas_cu)

        btn_editarmascota = findViewById(R.id.edit_mascotas)
        btn_agregarmascota = findViewById(R.id.btn_agregarmascotas)

        btn_editarmascota?.setOnClickListener{
            goToEditarMascota()
        }

        btn_agregarmascota?.setOnClickListener {
            goToAgregarMascota()
        }

        MyToolbar().show(this,"Mis mascotas",true)
    }

    private fun goToEditarMascota(){
        val i = Intent(this, EditarMascotasCu::class.java)
        startActivity(i)
    }

    private fun goToAgregarMascota(){
        val i = Intent(this, AgregarMascotasCu::class.java)
        startActivity(i)
    }

}