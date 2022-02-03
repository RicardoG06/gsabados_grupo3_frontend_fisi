package com.example.dogscloud.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class Trabajadores(@SerializedName("id") val id: String? = null,
                   @SerializedName("email") val email: String,
                   @SerializedName("name") val name: String,
                   @SerializedName("lastname") val lastname: String,
                   @SerializedName("dni") val dni: String,
                   @SerializedName("edad") val edad: String,
                   @SerializedName("phone") val phone: String? = null,
                   @SerializedName("password") val password: String,
                   @SerializedName("image") val image: String? = null,
                   @SerializedName("session_token") val sessionToken: String? = null,
                   @SerializedName("dia_trabajo") val dia_trabajo: String? = null,
                   @SerializedName("precio_x_hora_cuidado") val precio_x_hora_cuidado: String? = null,
                   @SerializedName("precio_x_hora_paseo") val precio_x_hora_paseo: String? = null,
                   @SerializedName("direccion") val direccion: String? = null,
                   @SerializedName("nombre_distrito") val nombre_ditrito: String? = null,
                   @SerializedName("popularidad") val popularidad: String? = null,
                    @SerializedName("roles") val roles: ArrayList<rol_pa_cu>? = null
                    )
{
    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Trabajadores(id=$id, email='$email', name='$name', lastname='$lastname', dni='$dni', edad='$edad', phone=$phone, password='$password', image=$image, sessionToken=$sessionToken, dia_trabajo=$dia_trabajo, precio_x_hora_cuidado=$precio_x_hora_cuidado, precio_x_hora_paseo=$precio_x_hora_paseo, direccion=$direccion, nombre_ditrito=$nombre_ditrito, popularidad=$popularidad, roles=$roles)"
    }

}