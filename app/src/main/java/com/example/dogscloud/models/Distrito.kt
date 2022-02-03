package com.example.dogscloud.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Distrito(@SerializedName("id") val id: String? = null,
               @SerializedName("nombre_distrito") val nombre_distrito: String)
{
    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Distrito(id=$id, nombre_distrito='$nombre_distrito')"
    }

}