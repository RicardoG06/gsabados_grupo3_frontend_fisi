package com.example.dogscloud.activities.Fragments.mascotas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.PantallaPaseadorActivity
import com.example.dogscloud.adapters.PerritosAdapter
import com.example.dogscloud.adapters.rolesAdapter
import com.example.dogscloud.models.User
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class MisMascotasFragment : Fragment() {
    var myView: View? = null
    var btn_editarmascota: Button? = null
    var btn_agregarmascota: Button? = null
    var recyclerViewPerritos: RecyclerView? = null
    var user : User? = null
    var adapter : PerritosAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_mis_mascotas, container, false)
        btn_agregarmascota = myView?.findViewById(R.id.btn_agregarmascotas)
        recyclerViewPerritos = myView?.findViewById(R.id.recyclewViewPerritos)
        recyclerViewPerritos?.layoutManager = LinearLayoutManager(requireContext())

        btn_editarmascota?.setOnClickListener{
            goToEditarMascota()
        }

        btn_agregarmascota?.setOnClickListener {
            goToAgregarMascota()
        }

        getUserFromSession()

        adapter = PerritosAdapter(requireContext() as Activity,user?.perritos!!)
        recyclerViewPerritos?.adapter = adapter

        return myView
    }

    private fun goToEditarMascota(){
        val i = Intent(getActivity(), EditarMascotasMenu::class.java)
        getActivity()?.startActivity(i)
    }

    private fun goToAgregarMascota(){
        val i = Intent(getActivity(), AgregarMascotasMenu::class.java)
        getActivity()?.startActivity(i)
    }


    private fun getUserFromSession(){

        val sharedPref = SharedPref(requireContext() as Activity)
        val gson = Gson()

        if(!sharedPref.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            user = gson.fromJson(sharedPref.getData("user"), User::class.java)
        }
    }
}
