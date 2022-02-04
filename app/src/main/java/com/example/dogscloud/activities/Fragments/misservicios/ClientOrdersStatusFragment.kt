package com.example.dogscloud.activities.Fragments.misservicios

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.adapters.OrdersClientAdapter
import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.OrdersProvider
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ClientOrdersStatusFragment : Fragment() {

    var myView: View? = null
    var ordersProvider: OrdersProvider? = null
    var user: User? = null
    var sharedPref : SharedPref? = null

    var recyclerViewOrders: RecyclerView? = null
    var adapter : OrdersClientAdapter? = null

    var status = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_client_orders_status, container, false)

        sharedPref = SharedPref(requireActivity())

        status = arguments?.getString("status")!!

        getUserFromSession()
        ordersProvider = OrdersProvider()

        recyclerViewOrders = myView?.findViewById(R.id.recyclerview_orders)
        recyclerViewOrders?.layoutManager = LinearLayoutManager(requireContext())

        getOrders()

        return myView
    }

    private fun getOrders(){
        ordersProvider?.getOrdersByClientAndStatus(user?.id!! , status)?.enqueue(object : Callback<ArrayList<CarritoCompra>>{
            override fun onResponse(
                call: Call<ArrayList<CarritoCompra>>,
                response: Response<ArrayList<CarritoCompra>>
            ) {
                if(response.body() != null){
                    val orders = response.body()
                    adapter = OrdersClientAdapter(requireActivity(), orders!!)
                    recyclerViewOrders?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<CarritoCompra>>, t: Throwable) {
                Toast.makeText(requireActivity(), "Error : ${t.message}", Toast.LENGTH_SHORT).show()
            }


        })
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d("MainActivity", "Usuario: $user")
        }
    }

}