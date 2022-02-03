package com.example.dogscloud.activities.Fragments.menu.programapaseo.MascotasPa

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu.AgregarMascotasCu
import com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu.EditarMascotasCu
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.adapters.PerritosAdapter
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class MisMascotasPa : AppCompatActivity() {

    var btn_editarmascota: Button? = null
    var btn_agregarmascota: Button? = null
    var recyclerViewPerritos: RecyclerView? = null
    var user : User? = null
    var adapter : PerritosAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_mascotas_pa)

        recyclerViewPerritos = findViewById(R.id.recyclewViewPerritos)
        recyclerViewPerritos?.layoutManager = LinearLayoutManager(this)
        btn_agregarmascota = findViewById(R.id.btn_agregarmascotas)

        btn_editarmascota?.setOnClickListener{
            goToEditarMascota()
        }

        btn_agregarmascota?.setOnClickListener {
            goToAgregarMascota()
        }

        MyToolbar().show(this,"Mis mascotas",true)

        getUserFromSession()

        adapter = PerritosAdapter(this,user?.perritos!!)
        recyclerViewPerritos?.adapter = adapter
    }

    private fun goToEditarMascota(){
        val i = Intent(this, EditarMascotasPa::class.java)
        startActivity(i)
    }

    private fun goToAgregarMascota(){
        val i = Intent(this, AgregarMascotasPa::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            user = gson.fromJson(sharedPref.getData("user"), User::class.java)
        }
    }
}