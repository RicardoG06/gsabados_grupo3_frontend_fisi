package com.example.dogscloud.models

import com.google.gson.annotations.SerializedName

class rol_ad_usu(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: String,
    @SerializedName("route") val route: String,) {

    override fun toString(): String {
        return "rol_ad_usu(id='$id', rol='$name', image='$image', route='$route')"
    }
}