package com.example.dogscloud.activities.Fragments.menu.programacuidado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.SolicitarCantidad
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import android.os.Bundle as Bundle1

class CuidadorEscogido : AppCompatActivity() {

    var trabajador: Trabajadores? = null
    var buttonGoToSolicitarCantidad: Button? = null
    val gson = Gson()

    var CircularimageView : CircleImageView? = null
    var textViewName: TextView? = null
    var textTelefono: TextView? = null
    var textCorreo: TextView? = null
    var textPopularidad: TextView? = null
    var textPrecioxhoracuidado: TextView? = null
    var textPrecioxhorapaseo: TextView? = null
    var textNombreDireccion: TextView? = null

    var sharedPref : SharedPref? = null

    override fun onCreate(savedInstanceState: Bundle1?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuidador_escogido)

        MyToolbar().show(this,"Cuidador",true)

        buttonGoToSolicitarCantidad = findViewById(R.id.button_go_to_solicitarcantidad)

        buttonGoToSolicitarCantidad?.setOnClickListener{ goToSolicitarCantidad(trabajador!!)}

        trabajador = gson.fromJson(intent.getStringExtra("trabajador"), Trabajadores::class.java)

            CircularimageView = findViewById(R.id.circleimage_user)
            textViewName = findViewById(R.id.text_name)
            textTelefono = findViewById(R.id.telefono)
            textCorreo = findViewById(R.id.correo)
            textPopularidad = findViewById(R.id.popularidad)
            textPrecioxhoracuidado = findViewById(R.id.precio_x_cuidado)
            textPrecioxhorapaseo = findViewById(R.id.precio_x_paseo)
            textNombreDireccion = findViewById(R.id.direccion)

            Glide.with(this).load(trabajador?.image).into(CircularimageView!!)
            textViewName?.text = "${trabajador?.name} ${trabajador?.lastname}"
            textTelefono?.text = trabajador?.phone
            textCorreo?.text = trabajador?.email
            textPopularidad?.text = trabajador?.popularidad
            textPrecioxhoracuidado?.text = trabajador?.precio_x_hora_cuidado
            textPrecioxhorapaseo?.text = trabajador?.precio_x_hora_paseo
            textNombreDireccion?.text = trabajador?.direccion


        }

    private fun goToSolicitarCantidad(trabajador: Trabajadores){

        val i = Intent(this, SolicitarCantidadSimpleCuidado::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}




