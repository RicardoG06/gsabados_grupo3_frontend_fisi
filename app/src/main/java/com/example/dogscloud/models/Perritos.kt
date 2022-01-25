package com.example.dogscloud.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Perritos(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("descripcion") val descripcion: String,
    @SerializedName("raza") val raza: String,
    @SerializedName("edad") val edad: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("id_user") val id_user: String?,
    @SerializedName("session_token") val sessionToken: String? = null,
    @SerializedName("is_available") val isAvailable: String? = null,
    ) {


        fun toJson(): String{
            return Gson().toJson(this)
        }

    override fun toString(): String {
        return "Perritos(id=$id, name='$name', descripcion='$descripcion', raza='$raza', edad='$edad', image='$image', id_user=$id_user, sessionToken=$sessionToken, isAvailable=$isAvailable)"
    }


}
