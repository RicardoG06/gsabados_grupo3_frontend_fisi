package com.example.dogscloud.activities.Fragments.menu

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.PantallaPaseadorActivity
import com.example.dogscloud.activities.Fragments.menu.programapaseo.PaseadorEscogido
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class InicioFragment : Fragment() {

    var myView: View? = null
    var btn_paseador: LinearLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_inicio, container, false)
        btn_paseador = myView?.findViewById(R.id.btn_paseador)

        btn_paseador?.setOnClickListener{
            goToPaseador()
        }

        return myView
    }

    private fun goToPaseador(){
        val i = Intent(getActivity(), PantallaPaseadorActivity::class.java)
        getActivity()?.startActivity(i)
    }
}