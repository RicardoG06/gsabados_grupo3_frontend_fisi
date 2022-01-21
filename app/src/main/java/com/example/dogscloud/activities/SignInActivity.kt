package com.example.dogscloud.activities

import android.content.Intent
import android.content.Intent.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.dogscloud.R
import com.example.dogscloud.activities.admin.home.AdminHomeActivity
import com.example.dogscloud.activities.client.home.AnuncioUnoActivity
import com.example.dogscloud.activities.client.home.MainActivity
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.UsersProvider
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    var botonGoToSingUp: Button? = null
    var editTextEmail: EditText? = null
    var editTextPassword: EditText? = null
    var buttonLogin: Button? = null
    var usersProvider = UsersProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        botonGoToSingUp = findViewById(R.id.buttonNewAcccount)
        editTextEmail = findViewById(R.id.userInput)
        editTextPassword = findViewById(R.id.passwordInput)
        buttonLogin = findViewById(R.id.btn_login)

        botonGoToSingUp?.setOnClickListener{
            goToSignUp()
        }

        buttonLogin?.setOnClickListener {
            login()
        }

        getUserFromSession()
    }

    private fun login(){
        val email = editTextEmail?.text.toString()
        val password = editTextPassword?.text.toString()

        if(isValidForm(email,password)){
            usersProvider.login(email,password)?.enqueue(object: Callback<ResponseHttp> {
                override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {

                    Log.d( "SignInActivity", "Response: ${response.body()}" )

                    if(response.body()?.isSuccess == true){
                        Toast.makeText(this@SignInActivity, response.body()?.message, Toast.LENGTH_LONG).show()

                        saveUserInSession(response.body()?.data.toString())

                    }
                    else{
                        Toast.makeText(this@SignInActivity,"Los datos no son correctos", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                    Log.d( "SignInActivity", "Hubo un error ${t.message}" )
                    Toast.makeText(this@SignInActivity, "Hubo un error ${t.message}", Toast.LENGTH_LONG).show()
                }
            })

        }
        else{
            Toast.makeText(this, "El formulario no es valido", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveUserInSession(data: String){
        val sharedPref = SharedPref(this)
        val gson = Gson()
        val user = gson.fromJson(data, User::class.java)
        sharedPref.save("user", user)

        if(user.roles?.size!! > 1){ //TIENE MAS DE UN ROL
            goToSelectRol()
        }
        else{ //SOLO UN ROL
            goToClientHome()
        }

    }

    fun String.isEmailValid(): Boolean{
        return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
    }

    private fun isValidForm(email: String, password: String): Boolean {
        if (email.isBlank()){
            return false
        }
        if(password.isBlank()){
            return false
        }
        if(!email.isEmailValid()){
            return false
        }
        return true
    }

    private fun getUserFromSession(){

        val sharedPref = SharedPref(this)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            val user = gson.fromJson(sharedPref.getData("user"), User::class.java)

            if(!sharedPref.getData("rol").isNullOrBlank()){
                //Si el usuario seleccion el rol
                val rol = sharedPref.getData("rol")?.replace("\"","")
                Log.d("SignInActivity", "ROL $rol")

                if(rol == "ADMINISTRADOR"){
                    goToAdminHome()
                } else if (rol == "CLIENTE") {
                    goToClientHome()
                }
            }
            else{
                goToClientHome()
            }
        }
    }

    private fun goToSignUp(){
        val i = Intent(this,SignUpActivity::class.java)
        startActivity(i)
    }

    private fun goToClientHome(){
        val i = Intent(this, MainActivity::class.java)
        i.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK //Eliminar historia de pantalla
        startActivity(i)
    }

    private fun goToAdminHome(){
        val i = Intent(this, AdminHomeActivity::class.java)
        i.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK //Eliminar historia de pantalla
        startActivity(i)
    }

    private fun goToSelectRol(){
        val i = Intent(this, SelectRolesActivity::class.java)
        i.flags = FLAG_ACTIVITY_NEW_TASK or FLAG_ACTIVITY_CLEAR_TASK //Eliminar historia de pantalla
        startActivity(i)
    }

}