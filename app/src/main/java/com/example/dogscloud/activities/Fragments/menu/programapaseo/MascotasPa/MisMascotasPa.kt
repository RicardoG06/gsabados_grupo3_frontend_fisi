package com.example.dogscloud.activities.Fragments.menu.programapaseo.MascotasPa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu.AgregarMascotasCu
import com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu.EditarMascotasCu
import com.example.dogscloud.activities.MyToolbar

class MisMascotasPa : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var btn_editarmascota: Button? = null
        var btn_agregarmascota: Button? = null

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_mascotas_pa)

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
        val i = Intent(this, EditarMascotasPa::class.java)
        startActivity(i)
    }

    private fun goToAgregarMascota(){
        val i = Intent(this, AgregarMascotasPa::class.java)
        startActivity(i)
    }
}