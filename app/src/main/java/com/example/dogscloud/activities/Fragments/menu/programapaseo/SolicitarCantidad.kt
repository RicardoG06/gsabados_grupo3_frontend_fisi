package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SeleccionarMascotaCu
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SolicitarCuidado
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.adapters.SolicitarCantidadCuAdapter
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class SolicitarCantidad : AppCompatActivity() {

    var floatingGoToSeleccionarMascota: FloatingActionButton? = null
    var buttonGoToSolicitarPaseo: Button? = null
    var trabajador : Trabajadores? = null
    var perri : Perritos? = null
    var recyclerViewPerritos: RecyclerView? = null
    var adapter : SolicitarCantidadCuAdapter? = null
    val gson = Gson()
    var user : User? = null
    var mascotas = ArrayList<Perritos>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_cantidad)

        MyToolbar().show(this,"Caracteristicas",true)

        recyclerViewPerritos = findViewById(R.id.recyclewViewPerritos)
        recyclerViewPerritos?.layoutManager = GridLayoutManager(this,1)

        floatingGoToSeleccionarMascota = findViewById(R.id.floating_go_to_seleccionarmascota)
        buttonGoToSolicitarPaseo = findViewById(R.id.button_go_to_solicitarpaseo)

        trabajador = gson.fromJson(intent.getStringExtra("trabajador"), Trabajadores::class.java)
        perri = gson.fromJson(intent.getStringExtra("perrito"), Perritos::class.java)


        Log.d("SolicitarCantidad" , "Nombre: ${trabajador?.name}")
        Log.d("SolicitarCantidad" , "Nombre: ${perri?.name}")

        mascotas.add(perri!!)

        getUserFromSession()


        floatingGoToSeleccionarMascota?.setOnClickListener{ goToSeleccionarMascota(trabajador!!) }
        buttonGoToSolicitarPaseo?.setOnClickListener{ goToSolicitarPaseo(trabajador!! , perri!!) }

        adapter = SolicitarCantidadCuAdapter(this@SolicitarCantidad,mascotas,trabajador!!)
        recyclerViewPerritos?.adapter = adapter

    }

    private fun goToSeleccionarMascota(trabajador: Trabajadores){
        val i = Intent(this, SeleccionarMascota::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun goToSolicitarPaseo(trabajador: Trabajadores, perri: Perritos){
        val i = Intent(this, SolicitarPaseo::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        i.putExtra("perrito", perri.toJson())
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
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