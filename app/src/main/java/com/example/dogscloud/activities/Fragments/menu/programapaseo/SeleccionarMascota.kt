package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu.AgregarMascotasCu
import com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu.MisMascotasCu
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SolicitarCantidadCu
import com.example.dogscloud.activities.Fragments.menu.programapaseo.MascotasPa.AgregarMascotasPa
import com.example.dogscloud.activities.Fragments.menu.programapaseo.MascotasPa.MisMascotasPa
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.adapters.PerritosAdapterConCheckPaseo
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class SeleccionarMascota : AppCompatActivity() {

    var buttonGoToSolicitarCantidad: Button? = null
    var AgregarMascotas: Button? = null
    var recyclerViewPerritos: RecyclerView? = null
    var trabajador : Trabajadores? = null
    var user : User? = null
    var perri : Perritos? = null
    var adapter : PerritosAdapterConCheckPaseo? = null
    val gson = Gson()
    var sharedPref: SharedPref? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_seleccionar_mascota)

        MyToolbar().show(this,"Seleccionar Mascota",false)

        sharedPref = SharedPref(this)

        recyclerViewPerritos = findViewById(R.id.recyclewViewPerritos)
        recyclerViewPerritos?.layoutManager = LinearLayoutManager(this)
        buttonGoToSolicitarCantidad = findViewById(R.id.button_go_to_solicitarcantidad)
        AgregarMascotas = findViewById(R.id.btn_agregarmascotas)



        trabajador = gson.fromJson(intent.getStringExtra("trabajador"), Trabajadores::class.java)

        getUserFromSession()

        buttonGoToSolicitarCantidad?.setOnClickListener{ getPerritoFromSession(trabajador!!) }
        AgregarMascotas?.setOnClickListener{ goToAgregarMascotas(trabajador!!) }

        Log.d("SeleccionarMascota" , "Nombre: ${trabajador?.name}")



        adapter = PerritosAdapterConCheckPaseo(this,user?.perritos!!)
        recyclerViewPerritos?.adapter = adapter


    }

    private fun getPerritoFromSession(trabajador: Trabajadores){
        if(!sharedPref?.getData("perrito").isNullOrBlank()){
            perri = gson.fromJson(sharedPref?.getData("perrito"), Perritos::class.java)
            Log.d("SeleccionarMascota", "Perrito: ${perri?.name}")
            goToSolicitarCantidad(trabajador,perri!!)
        }
        else{
            Toast.makeText(this,"Seleccione una mascota para continuar ", Toast.LENGTH_LONG).show()
        }
    }

    private fun goToSolicitarCantidad(trabajador: Trabajadores, perri : Perritos){
        val i = Intent(this, SolicitarCantidad::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        i.putExtra("perrito", perri.toJson())
        startActivity(i)
    }

    private fun goToAgregarMascotas(trabajador: Trabajadores){
        val i = Intent(this, AgregarMascotasPa::class.java)
        i.putExtra("trabajador", trabajador.toJson())
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

    fun resetValue(position: Int){
        val viewHolder = recyclerViewPerritos?.findViewHolderForAdapterPosition(position)
        val view = viewHolder?.itemView
        val imageViewCheck = view?.findViewById<ImageView>(R.id.imageview_check)
        imageViewCheck?.visibility = View.GONE
    }
}