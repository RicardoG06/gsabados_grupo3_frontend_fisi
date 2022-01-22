package com.example.dogscloud.activities.Fragments.menu.programacuidado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogscloud.R
import com.example.dogscloud.activities.MyToolbar

class SolicitarCuidado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_solicitar_cuidado)

        MyToolbar().show(this,"Dias de Cuidado",true)
    }
}