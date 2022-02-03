package com.example.dogscloud.adapters

import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.CuidadorEscogido
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SeleccionarMascotaCu
import com.example.dogscloud.activities.Fragments.menu.programacuidado.SolicitarCantidadSimpleCuidado
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.utils.SharedPref

class SolicitarCantidadCuAdapter(val context: Activity, val perritos: ArrayList<Perritos>, val trabajador: Trabajadores) : RecyclerView.Adapter<SolicitarCantidadCuAdapter.PerritosViewHolder>() {

    val sharedPreferences = SharedPref(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerritosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_cantperritos, parent, false)

        return PerritosViewHolder(view)
    }

    override fun getItemCount(): Int {
        return perritos.size
    }

    override fun onBindViewHolder(holder: PerritosViewHolder, position: Int) {
        val perrito = perritos[position] //CADA ROL

        holder.textViewPerrito.text = perrito.name
        Glide.with(context).load(perrito.image).into(holder.imageViewPerrito)
        holder.closeViewPerrito.setOnClickListener{goToSeleccionarCantidad()}
    }

    private fun goToSeleccionarCantidad(){
        val i = Intent(context, SolicitarCantidadSimpleCuidado::class.java)
        i.putExtra("trabajador", trabajador.toJson())
        context.startActivity(i)
    }

    class PerritosViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewPerrito : TextView
        val imageViewPerrito: ImageView
        val closeViewPerrito: ImageView

        init{
            textViewPerrito = view.findViewById(R.id.item_title)
            imageViewPerrito = view.findViewById(R.id.item_image)
            closeViewPerrito = view.findViewById(R.id.eliminar_mascota)
        }
    }

}
