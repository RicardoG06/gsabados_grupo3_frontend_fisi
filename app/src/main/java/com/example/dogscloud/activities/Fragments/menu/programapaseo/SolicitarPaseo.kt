package com.example.dogscloud.activities.Fragments.menu.programapaseo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.InfoCompra
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SolicitarCuidadoMascota.DatePickerFragment
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SolicitarCuidadoMascota.TimePickerFragment
import com.example.dogscloud.activities.Fragments.menu.programacuidado.map.ClientAddressMapActivity
import com.example.dogscloud.activities.Fragments.menu.programapaseo.map.ClientAddressMapActivity2
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class SolicitarPaseo : AppCompatActivity() {

    val TAG = "SolicitarPaseo"

    var trabajador : Trabajadores? = null
    var perri : Perritos? = null
    val gson = Gson()
    var user : User? = null

    var fecha : EditText? = null
    var hora : EditText? = null
    var cantHoras : EditText? = null
    var editTextRefPoint : EditText?  = null
    var editTextAddress : EditText? = null
    var btnSiguiente : Button? = null

    var addressLat = 0.0
    var addressLng = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_paseo)

        MyToolbar().show(this,"Dias de Paseo",true)

        trabajador = gson.fromJson(intent.getStringExtra("trabajador"), Trabajadores::class.java)
        perri = gson.fromJson(intent.getStringExtra("perrito"), Perritos::class.java)

        Log.d(TAG, "trabajador : ${trabajador?.name}")
        Log.d(TAG, "perri : ${perri?.name}")

        fecha = findViewById(R.id.fecha)
        hora = findViewById(R.id.hora)
        cantHoras = findViewById(R.id.cantidad_horas)
        editTextAddress = findViewById(R.id.edit1)
        editTextRefPoint = findViewById(R.id.edit2)
        btnSiguiente = findViewById(R.id.button_siguiente)


        fecha?.setOnClickListener{
            showDatePickerDialog()
        }

        hora?.setOnClickListener{
            ShowTimePickerDioalog()
        }

        editTextRefPoint?.setOnClickListener{
            goToAddressMap()
        }

        getUserFromSession()

        btnSiguiente?.setOnClickListener{
            createFechaHoraDireccion()
        }

    }

    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            user = gson.fromJson(sharedPref.getData("user"), User::class.java)
        }
    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment{day , month , year -> onDateSelected(day , month , year)}
        datePicker.show(supportFragmentManager, "datePicker")
    }

    fun onDateSelected(day:Int , month:Int , year:Int){
        var mes : Int
        mes = month + 1
        fecha?.setText("$day / $mes / $year")
    }

    private fun ShowTimePickerDioalog(){
        val timePicker = TimePickerFragment {onTimeSelected(it)}
        timePicker.show(supportFragmentManager,"time")
    }

    private fun onTimeSelected(time:String){
        hora?.setText(time)
    }

    private fun createFechaHoraDireccion(){
        val address = editTextAddress?.text.toString()
        val Fecha = fecha?.text.toString()
        val Hora = hora?.text.toString()
        val Cant_horas = cantHoras?.text.toString()
        val referencia = editTextRefPoint?.text.toString()

        if (isValidForm(address,Fecha,Hora,Cant_horas,referencia)){
            goToInformacionCompra(trabajador!!,perri!!,address,Fecha,Hora,Cant_horas,referencia)
        }
    }

    private fun isValidForm(address: String , Fecha : String , Hora : String , Cant_horas : String , referencia : String) : Boolean {
        if (address.isNullOrBlank()){
            Toast.makeText(this, "Ingresa la direccion", Toast.LENGTH_SHORT).show()
            return false
        }
        if (Fecha.isNullOrBlank()){
            Toast.makeText(this, "Ingresa la fecha de cuidado", Toast.LENGTH_SHORT).show()
            return false
        }
        if (Hora.isNullOrBlank()){
            Toast.makeText(this, "Ingresa la hora de inicio del cuidado", Toast.LENGTH_SHORT).show()
            return false
        }
        if (Cant_horas.isNullOrBlank()) {
            Toast.makeText(this, "Ingresa la cantidad de horas del cuidado", Toast.LENGTH_SHORT).show()
            return false
        }
        if (referencia.isNullOrBlank()) {
            Toast.makeText(this, "Ingresa una referencia", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->

        if (result.resultCode == Activity.RESULT_OK ){
            val data = result.data
            val city = data?.getStringExtra("city")
            val address = data?.getStringExtra("address")
            val country = data?.getStringExtra("country")
            addressLat = data?.getDoubleExtra("lat", 0.0)!!
            addressLng = data?.getDoubleExtra("lng", 0.0)!!

            editTextRefPoint?.setText("$address $city")

            Log.d(TAG , "City: $city")
            Log.d(TAG , "Address: $address")
            Log.d(TAG , "Country: $country")
            Log.d(TAG , "Lat: $addressLat")
            Log.d(TAG , "Lng: $addressLng")
        }
    }


    private fun goToAddressMap(){
        val i = Intent(this, ClientAddressMapActivity2::class.java)
        resultLauncher.launch(i)
    }

    private fun goToInformacionCompra(trabajador: Trabajadores , perri : Perritos , address: String , Fecha: String , Hora: String , Cant_horas: String , referencia: String){
        val i = Intent(this, InfoCompra2::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        i.putExtra("perrito", perri.toJson())
        i.putExtra("address", address)
        i.putExtra("Fecha", Fecha)
        i.putExtra("Hora", Hora)
        i.putExtra("Cant_horas", Cant_horas)
        i.putExtra("referencia", referencia)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }
}