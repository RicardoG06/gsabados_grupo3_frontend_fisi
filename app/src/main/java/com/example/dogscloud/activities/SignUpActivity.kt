package com.example.dogscloud.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dogscloud.R
import com.example.dogscloud.activities.client.home.AnuncioUnoActivity
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.UsersProvider
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    var botonGoToSingIn: Button? = null
    var editTextName: EditText? = null
    var editTextLastName: EditText? = null
    var editTextEmail: EditText? = null
    var editTextDni: EditText? = null
    var editTextEdad: EditText? = null
    var editTextPassword: EditText? = null
    var buttonRegister: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        botonGoToSingIn = findViewById(R.id.buttonLogIn)
        editTextName = findViewById(R.id.edittext_name)
        editTextLastName = findViewById(R.id.edittext_lastname)
        editTextEmail = findViewById(R.id.edittext_email)
        editTextDni = findViewById(R.id.edittext_dni)
        editTextEdad = findViewById(R.id.edittext_edad)
        editTextPassword = findViewById(R.id.edittext_password)
        buttonRegister = findViewById(R.id.btn_register)

        botonGoToSingIn?.setOnClickListener{
            goToSignIn()
        }

        buttonRegister?.setOnClickListener {
            register()
        }
    }

    private fun register(){
        val name = editTextName?.text.toString()
        val lastname = editTextLastName?.text.toString()
        val email = editTextEmail?.text.toString()
        val dni = editTextDni?.text.toString()
        val edad = editTextEdad?.text.toString()
        val password = editTextPassword?.text.toString()

        var usersProvider = UsersProvider()

        if(isValidForm(name,lastname,email,dni,edad,password)){
            val user = User(
                name = name,
                lastname = lastname,
                email = email,
                dni = dni,
                edad = edad,
                password = password
            )

            usersProvider.register(user)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>
                ) {

                    if(response.body()?.isSuccess == true){
                        saveUserInSession(response.body()?.data.toString())
                        goToRecommendation()
                    }

                    Toast.makeText(this@SignUpActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                    Log.d("SignUpActivity", "Response: ${response}")
                    Log.d("SignUpActivity", "Body: ${response.body()}")
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d("SignUpActivity", "Se produjo un error ${t.message}")
                    Toast.makeText(this@SignUpActivity, "Se produjo un error ${t.message}", Toast.LENGTH_LONG).show()
                }

            })
        }
        Log.d( "SignUpActivity", "El email es: $email" )
        Log.d( "SignUpActivity", "El password es: $password" )
        Log.d( "SignUpActivity", "El nombre es: $name" )
        Log.d( "SignUpActivity", "El dni es: $dni" )
        Log.d( "SignUpActivity", "El apellido es: $lastname" )
        Log.d( "SignUpActivity", "La edad es: $edad" )
    }

    private fun saveUserInSession(data: String){
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)
    }

    fun String.isEmailValid(): Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidForm(name: String,
                            lastname: String ,
                            email: String,
                            dni: String,
                            edad: String ,
                            password: String ): Boolean {
        if (name.isBlank()){
            Toast.makeText(this, "Coloque un nombre", Toast.LENGTH_LONG).show()
            return false
        }
        if (lastname.isBlank()){
            Toast.makeText(this, "Coloque un apellido", Toast.LENGTH_LONG).show()
            return false
        }
        if (email.isBlank()){
            Toast.makeText(this, "Coloque un email", Toast.LENGTH_LONG).show()
            return false
        }
        if (dni.isBlank()){
            Toast.makeText(this, "Coloque su DNI", Toast.LENGTH_LONG).show()
            return false
        }
        if(edad.isBlank()){
            Toast.makeText(this, "Coloque su edad", Toast.LENGTH_LONG).show()
            return false
        }
        if(password.isBlank()){
            Toast.makeText(this, "Coloque una contraseña", Toast.LENGTH_LONG).show()
            return false
        }
        if(!email.isEmailValid()){
            Toast.makeText(this, "Email incorrecto", Toast.LENGTH_LONG).show()
            return false
        }
        return true
    }

    private fun goToSignIn(){
        val i = Intent(this,SignInActivity::class.java)
        startActivity(i)
    }

    private fun goToRecommendation(){
        val i = Intent(this, AnuncioUnoActivity::class.java)
        startActivity(i)
    }
}