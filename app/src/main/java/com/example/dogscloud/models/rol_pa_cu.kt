package com.example.dogscloud.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class rol_pa_cu(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
) {
    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "rol_pa_cu(id='$id', name='$name')"
    }


}