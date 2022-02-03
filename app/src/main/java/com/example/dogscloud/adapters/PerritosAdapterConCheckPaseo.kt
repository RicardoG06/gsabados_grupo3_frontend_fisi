package com.example.dogscloud.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.SeleccionarMascota
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson

class PerritosAdapterConCheckPaseo(val context: Activity, val perritos: ArrayList<Perritos>) : RecyclerView.Adapter<PerritosAdapterConCheckPaseo.PerritosViewHolder>() {

    val gson = Gson()
    val sharedPref = SharedPref(context)
    var prev = 0
    var positionPerritoSesion = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_mascotas_con_check, parent, false)

        return PerritosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return perritos.size
    }

    override fun onBindViewHolder(holder: PerritosViewHolder, position: Int) {
        val perrito = perritos[position] //CADA PERRITO

        if(!sharedPref.getData("perrito").isNullOrBlank()){
            val perri = gson.fromJson(sharedPref.getData("perrito"), Perritos::class.java)

            if (perri.id == perrito.id){
                positionPerritoSesion = position
                holder.imageViewCheck.visibility = View.VISIBLE
            }
        }

        holder.textViewPerrito.text = perrito.name
        holder.textViewDescripcion.text = perrito.raza
        Glide.with(context).load(perrito.image).into(holder.imageViewPerrito)

        holder.itemView.setOnClickListener{
            (context as SeleccionarMascota).resetValue(prev)
            (context as SeleccionarMascota).resetValue(positionPerritoSesion)
            prev = position
            holder.imageViewCheck.visibility = View.VISIBLE
            saveMascota(perrito.toJson())
        }


    }

    class PerritosViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewPerrito : TextView
        val textViewDescripcion : TextView
        val imageViewPerrito: ImageView
        val imageViewCheck : ImageView

        init{
            textViewPerrito = view.findViewById(R.id.item_title)
            textViewDescripcion = view.findViewById(R.id.item_detal)
            imageViewPerrito = view.findViewById(R.id.item_image)
            imageViewCheck = view.findViewById(R.id.imageview_check)
        }
    }

    private fun saveMascota(data: String) {
        val perrito = gson.fromJson(data, Perritos::class.java )
        sharedPref.save("perrito", perrito)
    }


}