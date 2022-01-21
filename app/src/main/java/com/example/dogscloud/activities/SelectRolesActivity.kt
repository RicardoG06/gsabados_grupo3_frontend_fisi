package com.example.dogscloud.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.adapters.rolesAdapter
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class SelectRolesActivity : AppCompatActivity() {

    var recyclerViewRoles: RecyclerView? = null
    var user : User? = null
    var adapter : rolesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_roles)

        recyclerViewRoles = findViewById(R.id.recyclerview_roles)
        recyclerViewRoles?.layoutManager = LinearLayoutManager(this)

        getUserFromSession()

        adapter = rolesAdapter(this,user?.roles!!)
        recyclerViewRoles?.adapter = adapter

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