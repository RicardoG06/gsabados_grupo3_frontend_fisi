package com.example.dogscloud.activities.Fragments.mispedidos

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.adapters.ShoppingBagAdapter
import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MisPedidosFragment : Fragment() {

    var myView: View? = null
    var recyclerViewShoppingBag: RecyclerView? = null
    var textViewTotal: TextView? = null
    var buttonCompra : Button? = null

    var adapter: ShoppingBagAdapter? = null
    var sharedPref : SharedPref? = null
    var gson = Gson()
    var selectedProducts = ArrayList<CarritoCompra>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        myView =  inflater.inflate(R.layout.fragment_mis_pedidos, container, false)

        sharedPref = SharedPref(requireContext() as Activity)

        recyclerViewShoppingBag = myView?.findViewById(R.id.recyclerview_shoppingbag)
        textViewTotal = myView?.findViewById(R.id.textview_total)
        buttonCompra = myView?.findViewById(R.id.button_compra)

        recyclerViewShoppingBag?.layoutManager= LinearLayoutManager(requireContext())

        getProductsFromSharedPref()

        return myView
    }

    fun setTotal(total: Double){
        textViewTotal?.text = "S/. ${total}"
    }

    private fun getProductsFromSharedPref(){
        if(!sharedPref?.getData("order").isNullOrBlank()){
            val type = object : TypeToken<ArrayList<CarritoCompra>>() {}.type
            selectedProducts = gson.fromJson(sharedPref?.getData("order"),type)

            adapter = ShoppingBagAdapter(requireContext() as Activity,selectedProducts)
            recyclerViewShoppingBag?.adapter = adapter
        }
    }


}