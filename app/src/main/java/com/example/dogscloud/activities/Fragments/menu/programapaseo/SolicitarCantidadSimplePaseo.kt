package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson

class SolicitarCantidadSimplePaseo : AppCompatActivity() {

    var floatingGoToSeleccionarMascota: FloatingActionButton? = null
    var buttonGoToSolicitarPaseo: Button? = null
    var trabajador : Trabajadores? = null
    val gson = Gson()
    var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_cantidad_simple_paseo)

        MyToolbar().show(this,"Caracteristicas",true)

        floatingGoToSeleccionarMascota = findViewById(R.id.floating_go_to_seleccionarmascota)
        buttonGoToSolicitarPaseo = findViewById(R.id.button_go_to_solicitarpaseo)

        trabajador = gson.fromJson(intent.getStringExtra("trabajador"), Trabajadores::class.java)

        Log.d("PaseadorEscogido" , "Nombre: ${trabajador?.name}")

        getUserFromSession()


        floatingGoToSeleccionarMascota?.setOnClickListener{ goToSeleccionarMascota(trabajador!!) }
        buttonGoToSolicitarPaseo?.setOnClickListener{ goToSolicitarPaseo() }
    }

    private fun goToSeleccionarMascota(trabajador: Trabajadores){
        val i = Intent(this, SeleccionarMascota::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

    private fun goToSolicitarPaseo(){
        Toast.makeText(this@SolicitarCantidadSimplePaseo , "Debes seleccionar la mascota para poder pasar al siguiente apartado" , Toast.LENGTH_LONG).show()
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