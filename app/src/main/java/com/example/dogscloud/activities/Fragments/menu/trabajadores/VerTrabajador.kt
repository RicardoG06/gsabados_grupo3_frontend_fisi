package com.example.dogscloud.activities.Fragments.menu.trabajadores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class VerTrabajador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_trabajador)

        MyToolbar().show(this,"Trabajador",true)
    }
}