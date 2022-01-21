package com.example.dogscloud.adapters

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
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
import com.example.dogscloud.models.rol_ad_usu
import com.example.dogscloud.utils.SharedPref

class rolesAdapter(val context: Activity, val roles: ArrayList<rol_ad_usu>, ) : RecyclerView.Adapter<rolesAdapter.RolesViewHolder>() {

    val sharedPreferences = SharedPref(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RolesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview_roles, parent, false)

        return RolesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return roles.size
    }

    override fun onBindViewHolder(holder: RolesViewHolder, position: Int) {
        val rol = roles[position] //CADA ROL

        holder.textViewRol.text = rol.name
            Glide.with(context).load(rol.image).into(holder.imageViewRol)

            holder.itemView.setOnClickListener{
                goToRol(rol)
            }
    }

    private fun goToRol(rol: rol_ad_usu){
        if (rol.name == "ADMINISTRADOR"){
            sharedPreferences.save("rol", "ADMINISTRADOR")
            val i = Intent(context, AdminHomeActivity::class.java)
            context.startActivity(i)
        }
        else if (rol.name == "CLIENTE"){
            sharedPreferences.save("rol", "CLIENTE")
            val i = Intent(context, MainActivity::class.java)
            context.startActivity(i)
        }
    }

    class RolesViewHolder(view: View): RecyclerView.ViewHolder(view){

        val textViewRol : TextView
        val imageViewRol: ImageView

        init{
            textViewRol = view.findViewById(R.id.textview_rol)
            imageViewRol = view.findViewById(R.id.imageview_rol)
        }
    }
}