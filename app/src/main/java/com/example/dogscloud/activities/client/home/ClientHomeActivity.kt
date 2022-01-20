package com.example.dogscloud.activities.client.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.SignInActivity
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class ClientHomeActivity : AppCompatActivity() {

    private val TAG = "ClientHomeActivity"
    var buttonLogout : Button? = null
    var sharedPref: SharedPref? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_home)
        sharedPref = SharedPref(this)
        buttonLogout = findViewById(R.id.btn_logout)

        buttonLogout?.setOnClickListener { logout() }

        getUserFromSession()
    }
    private fun logout(){
        sharedPref?.remove("user")
        val i = Intent(this, SignInActivity::class.java)
        startActivity(i)
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            val user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d(TAG, "Usuario: $user")
        }

    }
}