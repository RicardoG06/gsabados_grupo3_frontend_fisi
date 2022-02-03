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
import com.example.dogscloud.activities.admin.home.AdminHomeActivity
import com.example.dogscloud.activities.client.home.MainActivity
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.User
import com.example.dogscloud.models.rol_ad_usu
import com.example.dogscloud.utils.SharedPref

class PerritosAdapter(val context: Activity, val perritos: ArrayList<Perritos>) : RecyclerView.Adapter<PerritosAdapter.PerritosViewHolder>() {

    val sharedPreferences = SharedPref(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_mascotas, parent, false)

        return PerritosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return perritos.size
    }

    override fun onBindViewHolder(holder: PerritosViewHolder, position: Int) {
        val perrito = perritos[position] //CADA ROL

        holder.textViewPerrito.text = perrito.name
        holder.textViewDescripcion.text = perrito.raza
        Glide.with(context).load(perrito.image).into(holder.imageViewPerrito)

    }

    class PerritosViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewPerrito : TextView
        val textViewDescripcion : TextView
        val imageViewPerrito: ImageView

        init{
            textViewPerrito = view.findViewById(R.id.item_title)
            textViewDescripcion = view.findViewById(R.id.item_detal)
            imageViewPerrito = view.findViewById(R.id.item_image)
        }
    }



}