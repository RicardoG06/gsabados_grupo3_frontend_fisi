package com.example.dogscloud.activities.Fragments.mispedidos

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.adapters.PerritosAdapter
import com.example.dogscloud.adapters.PerritosAdapterConCheckPaseo
import com.example.dogscloud.adapters.ShoppingBagAdapter
import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.OrdersProvider
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MisPedidosFragment : Fragment() {

    var myView: View? = null
    var recyclerViewShoppingBag: RecyclerView? = null
    var textViewTotal: TextView? = null
    var buttonCompra: Button? = null

    var ordersProvider: OrdersProvider? = null

    var adapter: ShoppingBagAdapter? = null
    var sharedPref: SharedPref? = null
    var gson = Gson()
    var selectedProducts = ArrayList<CarritoCompra>()
    var user: User? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_pedidos, container, false)

        sharedPref = SharedPref(requireContext() as Activity)

        getUserFromSession()

        recyclerViewShoppingBag = myView?.findViewById(R.id.recyclerview_shoppingbag)
        textViewTotal = myView?.findViewById(R.id.textview_total)
        buttonCompra = myView?.findViewById(R.id.button_compra)

        ordersProvider = OrdersProvider()

        recyclerViewShoppingBag?.layoutManager = LinearLayoutManager(requireContext())


        buttonCompra?.setOnClickListener {
            createOrder()
        }
        getProductsFromSharedPref()

        return myView
    }

    fun setTotal(total: Double) {
        textViewTotal?.text = "S/. ${total}"
    }

    private fun createOrder() {
        goToPaymentForm()
        //for (p in selectedProducts){

        //  Log.d("MisPedidosFragment","${p.nombre_cuidador}")

        //val order = CarritoCompra(
        //  id_user = user?.id,
        //  nombre_cuidador = p.nombre_cuidador,
        //   popularidad_cuidador = p.popularidad_cuidador,
        //  precio_x_hora_cuidador = p.precio_x_hora_cuidador,
        //precio_x_hora_paseador = p.precio_x_hora_paseador,
        //imagen_trabajador = p.imagen_trabajador,
        //precio_total = p.precio_total,
        //nombre_perrito = p.nombre_perrito,
        //raza_perrito = p.raza_perrito,
        //fecha_cuidado = p.fecha_cuidado,
        //hora_inicio = p.hora_inicio,
        //horas_servicio = p.horas_servicio,
        //direccion_cliente = p.direccion_cliente,
        //referencia_cliente = p.referencia_cliente,
        //tipo_trabajador = p.tipo_trabajador,
        //)

        //ordersProvider?.create(order)?.enqueue(object : Callback<ResponseHttp>{
        //  override fun onResponse(
        //    call: Call<ResponseHttp>,
        //     response: Response<ResponseHttp>
        // ) {

        //    if(response.body()!= null){
        //       Toast.makeText(getActivity(), "${response.body()?.message}", Toast.LENGTH_SHORT).show()
        // }
        // else{
        //   Toast.makeText(getActivity(), "Ocurrio un error en la peticion", Toast.LENGTH_SHORT).show()
        // }
        //   }
        //   override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
        //  Toast.makeText(getActivity(), "Error: ${t.message}", Toast.LENGTH_SHORT).show()
        // }

        //  })
        //}
    }

    private fun goToPaymentForm(){
        val i = Intent(getActivity(),ClientPaymentFormActivity::class.java)
        startActivity(i)
    }

    private fun getProductsFromSharedPref(){
        if(!sharedPref?.getData("order").isNullOrBlank()){
            val type = object : TypeToken<ArrayList<CarritoCompra>>() {}.type
            selectedProducts = gson.fromJson(sharedPref?.getData("order"),type)

            adapter = ShoppingBagAdapter(requireContext() as Activity,selectedProducts)
            recyclerViewShoppingBag?.adapter = adapter
        }
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d("MisPedidosFragment", "Usuario: $user")
        }
    }


}