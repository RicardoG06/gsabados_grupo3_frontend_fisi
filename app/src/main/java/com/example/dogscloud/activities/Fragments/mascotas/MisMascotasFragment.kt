package com.example.dogscloud.activities.Fragments.mascotas

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.PantallaPaseadorActivity

class MisMascotasFragment : Fragment() {
    var myView: View? = null
    var btn_editarmascota: Button? = null
    var btn_agregarmascota: Button? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_mis_mascotas, container, false)
        btn_editarmascota = myView?.findViewById(R.id.edit_mascotas)
        btn_agregarmascota = myView?.findViewById(R.id.btn_agregarmascotas)


        btn_editarmascota?.setOnClickListener{
            goToEditarMascota()
        }

        btn_agregarmascota?.setOnClickListener {
            goToAgregarMascota()
        }

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
}
