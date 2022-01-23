package com.example.dogscloud.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dogscloud.R
import com.example.dogscloud.activities.SaveImageActivity
import com.example.dogscloud.activities.SignInActivity
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.UsersProvider
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarMascotaInicio : AppCompatActivity() {


        var edit_nombremascota: EditText? = null
        var edit_descripcion: EditText? = null
        var edit_raza: EditText? = null
        var edit_edad: EditText? = null
        var circleImage_user: CircleImageView? = null
        var btn_agregarmascotas: Button? = null
        var btn_siguiente: Button? = null
        var sharedPref: SharedPref? = null
        var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mascota_inicio)

        sharedPref = SharedPref(this)
        edit_nombremascota = findViewById(R.id.edit_nombremascota)
        edit_descripcion = findViewById(R.id.edit_descripcion)
        edit_raza = findViewById(R.id.edit_raza)
        edit_edad = findViewById(R.id.edit_edad)
        circleImage_user = findViewById(R.id.circleImage_user)
        btn_agregarmascotas = findViewById(R.id.btn_agregarmascotas)
        btn_siguiente = findViewById(R.id.btn_siguiente)

        getUserFromSession()

        btn_agregarmascotas?.setOnClickListener{
            register()
        }

        btn_siguiente?.setOnClickListener {
            goToRecomendado1()
        }
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
        }
    }

    fun register(){
        val name = edit_nombremascota?.text.toString()
        val descripcion = edit_descripcion?.text.toString()
        val raza = edit_raza?.text.toString()
        val edad = edit_edad?.text.toString()
        val id_user = user?.id

        var usersProvider = UsersProvider()

        if(isValidForm(name,descripcion,raza, edad)){
            val perrito = Perritos(
                name = name,
                descripcion = descripcion,
                raza = raza,
                edad = edad,
                id_user = id_user?.toInt()
            )

            usersProvider.registerP(perrito)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {

                    Toast.makeText(this@AgregarMascotaInicio, response.body()?.message, Toast.LENGTH_LONG).show()
                    Toast.makeText(this@AgregarMascotaInicio, "Mascota agregada correctamente", Toast.LENGTH_LONG).show()
                    Log.d("AgregarMascotaInicio", "Response: ${response}")
                    Log.d("AgregarMascotaInicio", "Body: ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d("AgregarMascotaInicio", "Se produjo un error ${t.message}")
                    Toast.makeText(this@AgregarMascotaInicio, "Se produjo un error ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
        Log.d( "AgregarMascotaInicio", "El email es: $name" )
        Log.d( "AgregarMascotaInicio", "El password es: $descripcion" )
        Log.d( "AgregarMascotaInicio", "El nombre es: $raza" )
        Log.d( "AgregarMascotaInicio", "El dni es: $edad" )
    }

    private fun isValidForm(name: String,
                            descripcion: String ,
                            raza: String,
                            edad: String ): Boolean {
        if (name.isBlank()){
            Toast.makeText(this, "Coloque un nombre", Toast.LENGTH_LONG).show()
            return false
        }
        if (descripcion.isBlank()){
            Toast.makeText(this, "Coloque una descripcion", Toast.LENGTH_LONG).show()
            return false
        }
        if (raza.isBlank()){
            Toast.makeText(this, "Coloque su raza", Toast.LENGTH_LONG).show()
            return false
        }
        if (edad.isBlank()){
            Toast.makeText(this, "Coloque su edad", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    fun goToRecomendado1(){
        val i = Intent(this, AnuncioUnoActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(i)
    }

}