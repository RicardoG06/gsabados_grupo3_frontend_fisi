package com.example.dogscloud.activities.Fragments.menu.programapaseo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class SolicitarPaseo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_paseo)

        MyToolbar().show(this,"Dias de Paseo",true)
    }
}