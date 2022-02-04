package com.example.dogscloud.adapters

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.CuidadorEscogido
import com.example.dogscloud.activities.Fragments.menu.trabajadores.VerTrabajador
import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.models.Trabajadores

class OrdersClientAdapter(val context: Activity, val orders: ArrayList<CarritoCompra>) : RecyclerView.Adapter<OrdersClientAdapter.OrdersClientViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdersClientViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_orders, parent, false)

        return OrdersClientViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrdersClientViewHolder, position: Int) {
        val order = orders[position] //CADA ROL

        holder.textViewOrder.text = "Orden #${order.id_orden}"
        holder.textViewDate.text = "${order.fecha_cuidado}"
        holder.textViewNombreTrab.text = "${order.nombre_cuidador}"
        holder.textViewAdress.text = "${order.direccion_cliente}"

        holder.itemView.setOnClickListener{

        }

    }


    class OrdersClientViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewOrder : TextView
        val textViewDate : TextView
        val textViewNombreTrab: TextView
        val textViewAdress : TextView

        init{
            textViewOrder = view.findViewById(R.id.textview_order_id)
            textViewDate = view.findViewById(R.id.textview_date)
            textViewNombreTrab = view.findViewById(R.id.nombre_trabajador)
            textViewAdress = view.findViewById(R.id.direccion)
        }
    }
}