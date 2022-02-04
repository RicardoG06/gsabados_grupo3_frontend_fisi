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
import com.example.dogscloud.models.Trabajadores

class TrabajadoresAdapterGeneral(val context: Activity, val trabajadores: ArrayList<Trabajadores>) : RecyclerView.Adapter<TrabajadoresAdapterGeneral.TrabajadoresViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrabajadoresViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_trabajadores, parent, false)

        return TrabajadoresViewHolder(view)
    }

    override fun getItemCount(): Int {
        return trabajadores.size
    }

    override fun onBindViewHolder(holder: TrabajadoresViewHolder, position: Int) {
        val trabajador = trabajadores[position] //CADA ROL

        holder.textViewTrabajador.text = trabajador.name
        holder.textViewDistrito.text = trabajador.nombre_ditrito
        holder.textViewPopularidad.text = trabajador.popularidad
        Glide.with(context).load(trabajador.image).into(holder.imageViewTrabajador)

        holder.itemView.setOnClickListener{goToDetail(trabajador)}

    }

    private fun goToDetail(trabajador: Trabajadores){

        val i = Intent(context, VerTrabajador::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        context.startActivity(i)
    }


    class TrabajadoresViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewTrabajador : TextView
        val textViewDistrito : TextView
        val textViewPopularidad: TextView
        val imageViewTrabajador : ImageView

        init{
            textViewTrabajador = view.findViewById(R.id.item_trabajador)
            textViewDistrito = view.findViewById(R.id.item_distrito)
            textViewPopularidad = view.findViewById(R.id.item_puntaje)
            imageViewTrabajador = view.findViewById(R.id.item_image)
        }
    }
}