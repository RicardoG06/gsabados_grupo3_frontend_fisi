package com.example.dogscloud.activities.Fragments.cerrarsesion

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dogscloud.R
import com.example.dogscloud.activities.SignInActivity
import com.example.dogscloud.models.User
import com.google.gson.Gson

class CerrarSesionFragment : Fragment() {

    private val TAG = "ClientHomeActivity"
    var myView: View? = null
    var btn_cerrarsesion: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_cerrar_sesion, container, false)
        btn_cerrarsesion = myView?.findViewById(R.id.btn_logout)

        return myView
    }

}