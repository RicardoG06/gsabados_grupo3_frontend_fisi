package com.example.dogscloud.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.mispedidos.MisPedidosFragment
import com.example.dogscloud.activities.admin.home.AdminHomeActivity
import com.example.dogscloud.activities.client.home.MainActivity
import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.User
import com.example.dogscloud.models.rol_ad_usu
import com.example.dogscloud.utils.SharedPref

class ShoppingBagAdapter(val context: Activity, val carrito_compra: ArrayList<CarritoCompra>) : RecyclerView.Adapter<ShoppingBagAdapter.ShoppingBagViewHolder>() {

    val sharedPreferences = SharedPref(context)

    //init {
      //  (context as MisPedidosFragment).setTotal(getTotal())
    //}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingBagViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_shopping_bag, parent, false)

        return ShoppingBagViewHolder(view)
    }

    override fun getItemCount(): Int {
        return carrito_compra.size
    }

    override fun onBindViewHolder(holder: ShoppingBagViewHolder, position: Int) {
        val car_compra = carrito_compra[position] //CADA ROL

        holder.textViewTrabajador.text = car_compra.nombre_cuidador
        holder.textViewTipoTrabajador.text = car_compra.tipo_trabajador
        holder.textViewNombrePerrito.text = car_compra.nombre_perrito
        holder.textViewPrecioT.text = "S/. ${car_compra.precio_total}"

        Glide.with(context).load(car_compra.imagen_trabajador).into(holder.imageViewTrabajador)

        holder.imageViewDelete.setOnClickListener{deleteItem(position)}

    }

    private fun getTotal(): Double{
        var total = 0.0
        for (p in carrito_compra){
            total = total + (p.precio_total?.toInt()!!)
        }
        return total
    }

    private fun deleteItem(position: Int){

        carrito_compra.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeRemoved(position, carrito_compra.size)
        sharedPreferences.save("order", carrito_compra)
        //(context as MisPedidosFragment).setTotal(getTotal())

    }

    class ShoppingBagViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewTrabajador : TextView
        val textViewTipoTrabajador : TextView
        val textViewNombrePerrito : TextView
        val textViewPrecioT : TextView
        val imageViewTrabajador: ImageView
        val imageViewDelete: ImageView

        init{
            textViewTrabajador = view.findViewById(R.id.nameTrabajador)
            textViewTipoTrabajador = view.findViewById(R.id.tipoTrabajador)
            textViewNombrePerrito = view.findViewById(R.id.nombrePerrito)
            textViewPrecioT = view.findViewById(R.id.textview_price)
            imageViewTrabajador = view.findViewById(R.id.imageview_trabajador)
            imageViewDelete = view.findViewById(R.id.imageview_delete)
        }
    }



}