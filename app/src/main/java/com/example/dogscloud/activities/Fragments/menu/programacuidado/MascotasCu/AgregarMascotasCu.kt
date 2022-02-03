package com.example.dogscloud.activities.Fragments.menu.programacuidado.MascotasCu

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SeleccionarMascotaCu
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SolicitarCantidadCu
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.activities.client.home.AnuncioUnoActivity
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.PerritosProvider
import com.example.dogscloud.providers.UsersProvider
import com.example.dogscloud.utils.SharedPref
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.tommasoberlose.progressdialog.ProgressDialogFragment
import de.hdodenhof.circleimageview.CircleImageView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class AgregarMascotasCu : AppCompatActivity() {

    var edit_nombremascota: EditText? = null
    var edit_descripcion: EditText? = null
    var edit_raza: EditText? = null
    var edit_edad: EditText? = null
    var circleImage_user: CircleImageView? = null
    var btn_agregarmascotas: Button? = null
    var btn_siguiente: Button? = null
    var user: User? = null
    var imageFile: File? = null
    var perritoProvider : PerritosProvider? = null
    var userprovider : UsersProvider? = null

    var trabajador : Trabajadores? = null
    val gson = Gson()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_mascotas_cu)

        MyToolbar().show(this,"Agregar una mascota",true)

        trabajador = gson.fromJson(intent.getStringExtra("trabajador"), Trabajadores::class.java)

        edit_nombremascota = findViewById(R.id.edit_nombremascota)
        edit_descripcion = findViewById(R.id.edit_descripcion)
        edit_raza = findViewById(R.id.edit_raza)
        edit_edad = findViewById(R.id.edit_edad)
        circleImage_user = findViewById(R.id.circleImage_user)
        btn_agregarmascotas = findViewById(R.id.btn_agregarmascotas)
        perritoProvider= PerritosProvider()
        userprovider = UsersProvider()

        getUserFromSession()

        circleImage_user?.setOnClickListener{
            selectImage()
        }

        btn_agregarmascotas?.setOnClickListener{
            registrarPerrito()
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

    fun registrarPerrito(){
        val name = edit_nombremascota?.text.toString()
        val descripcion = edit_descripcion?.text.toString()
        val raza = edit_raza?.text.toString()
        val edad = edit_edad?.text.toString()
        val id_user = user?.id
        val files = imageFile

        if(isValidForm(name,descripcion,raza, edad)){

            val perrito = Perritos(
                name = name,
                descripcion = descripcion,
                raza = raza,
                edad = edad,
                id_user = id_user
            )

            ProgressDialogFragment.showProgressBar(this)

            perritoProvider?.create(files!!,perrito)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {
                    Toast.makeText(this@AgregarMascotasCu, response.body()?.message, Toast.LENGTH_LONG).show()
                    Toast.makeText(this@AgregarMascotasCu, "Mascota agregada correctamente", Toast.LENGTH_LONG).show()
                    ProgressDialogFragment.hideProgressBar(this@AgregarMascotasCu)
                    Log.d("AgregarMascotaInicio", "Response: ${response}")
                    Log.d("AgregarMascotaInicio", "Body: ${response.body()}")

                    Log.d("AgregarMascotaInicio", "email: ${user?.email}")
                    Log.d("AgregarMascotaInicio", "pass: ${user?.password}")

                    login()

                    if(response.body()?.isSuccess == true){
                        ResetForm()
                    }
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    ProgressDialogFragment.hideProgressBar(this@AgregarMascotasCu)
                    Log.d("AgregarMascotaInicio", "Se produjo un error ${t.message}")

                }
            })

        }
        Log.d( "AgregarMascotaInicio", "El email es: $name" )
        Log.d( "AgregarMascotaInicio", "El password es: $descripcion" )
        Log.d( "AgregarMascotaInicio", "El nombre es: $raza" )
        Log.d( "AgregarMascotaInicio", "El dni es: $edad" )

    }


    private fun ResetForm(){
        edit_nombremascota?.setText("")
        edit_descripcion?.setText("")
        edit_raza?.setText("")
        edit_edad?.setText("")
        edit_descripcion?.setText("")
        imageFile = null
        circleImage_user?.setImageResource(R.drawable.camara)
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


    private fun selectImage(){
        ImagePicker.with(this)
            .crop()
            .compress(1024)
            .maxResultSize(1080, 1080)
            .createIntent { intent ->
                startImageForResult.launch(intent)
            }
    }

    private val startImageForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result: ActivityResult ->
        val resultCode = result.resultCode
        val data = result.data

        if ( resultCode == Activity.RESULT_OK){
            val fileUri = data?.data
            imageFile = File(fileUri?.path) // EL ARCHIVO QUE VAMOS A GUARDAR COMO IMAGEN EN EL SERVIDOR
            circleImage_user?.setImageURI(fileUri)
        }
        else if (resultCode == ImagePicker.RESULT_ERROR){
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Tarea de cancelo", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveUserInSession(data: String){
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    fun login(){
        val email = user?.email

        Log.d( "AgregarMascotaInicio", "Email dentro del login: ${email}" )

        userprovider?.loginValidacion(email!!)?.enqueue(object: Callback<ResponseHttp> {
            override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                Log.d( "AgregarMascotaInicio", "Response: ${response.body()}" )

                if(response.body()?.isSuccess == true){
                    Toast.makeText(this@AgregarMascotasCu, response.body()?.message, Toast.LENGTH_LONG).show()

                    saveUserInSession(response.body()?.data.toString())
                    goToSeleccionarMascota(trabajador!!)

                } else{
                    Toast.makeText(this@AgregarMascotasCu,"Los datos no son correctos", Toast.LENGTH_LONG).show()
                }


            }

            override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                Log.d( "SignInActivity", "Hubo un error ${t.message}" )
                Toast.makeText(this@AgregarMascotasCu, "Hubo un error ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun goToSeleccionarMascota(trabajador: Trabajadores){
        val i = Intent(this, SeleccionarMascotaCu::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        startActivity(i)
    }
}
