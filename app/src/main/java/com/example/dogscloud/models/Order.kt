package com.example.dogscloud.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class Order(
    @SerializedName("id_user") var id_user: String? = null,
    @SerializedName("compra") var compra: ArrayList<CarritoCompra>? = null,

    ){

    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "Order(id_user=$id_user, compra=$compra)"
    }


}