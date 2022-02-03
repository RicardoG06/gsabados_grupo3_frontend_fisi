package com.example.dogscloud.activities.Fragments.menu.programacuidado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.InicioFragment
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.activities.client.home.MainActivity
import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class InfoCompra : AppCompatActivity() {

    var carrito_compra : CarritoCompra? = null
    var trabajador : Trabajadores? = null
    var perri : Perritos? = null
    var fecha : String? = null
    var hora : String? = null
    var cantHoras : String? = null
    var referencia : String?  = null
    var direccion : String? = null
    val gson = Gson()

    var sharedPref : SharedPref? = null
    var selectedProducts = ArrayList<CarritoCompra>()

    var editTextCuidador : TextView? = null
    var editTextPopularidad : TextView? = null
    var editTextPrecioxHora : TextView? = null
    var editTextPrecioTotal : TextView? = null
    var editTextPerritoAcuidar: TextView? = null
    var editTextFechaCuidado : TextView? = null
    var editTextHoraInicio : TextView? = null
    var editTextHorasServicio : TextView? = null
    var editTextDireccion : TextView? = null
    var editTextReferencia : TextView? = null
    var buttonCarrito : Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_compra)

        MyToolbar().show(this,"Informacion Pedido",true)

        trabajador = gson.fromJson(intent.getStringExtra("trabajador"), Trabajadores::class.java)
        perri = gson.fromJson(intent.getStringExtra("perrito"), Perritos::class.java)
        fecha = intent.getStringExtra("Fecha")
        hora = intent.getStringExtra("Hora")
        cantHoras = intent.getStringExtra("Cant_horas")
        referencia = intent.getStringExtra("referencia")
        direccion = intent.getStringExtra("address")
        sharedPref = SharedPref(this)

        Log.d("InfoCompra", "trabajador : ${trabajador?.name}")
        Log.d("InfoCompra", "perri : ${perri?.name}")
        Log.d("InfoCompra", "fecha : $fecha")
        Log.d("InfoCompra", "hora : $hora")
        Log.d("InfoCompra", "cantHoras : $cantHoras")
        Log.d("InfoCompra", "direccion : $direccion")
        Log.d("InfoCompra", "referencia : $referencia")


        editTextCuidador = findViewById(R.id.cuidador)
        editTextPopularidad = findViewById(R.id.popularidad)
        editTextPrecioxHora = findViewById(R.id.precio_x_hora_cuidado)
        editTextPrecioTotal = findViewById(R.id.precio_Total)
        editTextPerritoAcuidar = findViewById(R.id.nombre_perrito)
        editTextFechaCuidado = findViewById(R.id.fecha_cuidado)
        editTextHoraInicio = findViewById(R.id.hora_inicio)
        editTextHorasServicio = findViewById(R.id.hora_servicio)
        editTextDireccion = findViewById(R.id.direccion_cliente)
        editTextReferencia = findViewById(R.id.referencia_cliente)
        buttonCarrito = findViewById(R.id.button_go_to_añadir_carrito)

        //Agregando la data a los Textview para su vizualizacion en pantalla
        editTextCuidador?.setText(trabajador?.name)
        editTextPopularidad?.setText(trabajador?.popularidad)
        editTextPrecioxHora?.setText(trabajador?.precio_x_hora_cuidado)

            //
            var cantHor = cantHoras?.toInt()
            var horasTra = trabajador?.precio_x_hora_cuidado
            var horasT = horasTra?.toInt()

            Log.d("InfoCompra", "horasTra : $horasT")
            Log.d("InfoCompra", "cantHor : $cantHor")

            var precioTotal = cantHor!! * horasT!!
            var precioT= precioTotal.toString()

            Log.d("InfoCompra", "Total : $precioT")

            precioTotal.toString()
            //
        editTextPrecioTotal?.setText(precioT)
        editTextPerritoAcuidar?.setText(perri?.name)
        editTextFechaCuidado?.setText(fecha)
        editTextHoraInicio?.setText(hora)
        editTextHorasServicio?.setText(cantHoras)
        editTextDireccion?.setText(direccion)
        editTextReferencia?.setText(referencia)

        //Agregando al array Compra

        carrito_compra = CarritoCompra(
            nombre_cuidador = trabajador?.name,
            popularidad_cuidador = trabajador?.popularidad,
            precio_x_hora_cuidador = trabajador?.precio_x_hora_cuidado,
            imagen_trabajador = trabajador?.image,
            precio_total = precioT,
            nombre_perrito = perri?.name,
            raza_perrito = perri?.raza,
            fecha_cuidado = fecha,
            hora_inicio = hora,
            hora_servicio = cantHoras,
            direccion_cliente = direccion,
            referencia_cliente = referencia,
            tipo_trabajador = "Cuidador"
        )

        Log.d("InfoCompra","Tipo de Trabajador : ${carrito_compra?.tipo_trabajador}")

        buttonCarrito?.setOnClickListener{ addToBag()}
        getProductsFromSharedPref()

    }

    private fun getProductsFromSharedPref(){
        if(!sharedPref?.getData("order").isNullOrBlank()){
            val type = object : TypeToken<ArrayList<CarritoCompra>>() {}.type
            selectedProducts = gson.fromJson(sharedPref?.getData("order"),type)

            for ( p in selectedProducts ){
                Log.d("InfoCompra", "Shared pref: $p" )
            }
        }
    }

    private fun addToBag(){
        selectedProducts.add(carrito_compra!!)
        sharedPref?.save("order", selectedProducts)
        goToPrincipal()
        Toast.makeText(this, "Servicio agregado a Pedidos", Toast.LENGTH_LONG).show()
    }

    private fun goToPrincipal(){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }
}