package com.example.dogscloud.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

class User(
    @SerializedName("id") val id: String? = null,
    @SerializedName("name") val name: String,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("email") val email: String,
    @SerializedName("dni") val dni: String,
    @SerializedName("edad") val edad: String,
    @SerializedName("phone") val phone: String? = null,
    @SerializedName("password") val password: String,
    @SerializedName("image") val image: String? = null,
    @SerializedName("session_token") val sessionToken: String? = null,
    @SerializedName("is_available") val isAvailable: String? = null,
    @SerializedName("roles") val roles: ArrayList<rol_ad_usu>? = null,
    @SerializedName("perritos") val perritos: ArrayList<Perritos>? = null
) {


    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "User(id=$id, name='$name', lastname='$lastname', email='$email', dni='$dni', edad='$edad', phone=$phone, password='$password', image=$image, sessionToken=$sessionToken, isAvailable=$isAvailable, roles=$roles, perritos=$perritos)"
    }
}