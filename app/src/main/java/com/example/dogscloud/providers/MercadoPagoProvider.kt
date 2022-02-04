package com.example.dogscloud.providers

import com.example.dogscloud.api.MercadoPagoApiRoutes
import com.example.dogscloud.models.MercadoPagoCardTokenBody
import com.example.dogscloud.routes.MercadoPagoRoutes
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call

class MercadoPagoProvider {

    var mercadoPagoRoutes: MercadoPagoRoutes? = null

    init {
        val api = MercadoPagoApiRoutes()
        mercadoPagoRoutes = api.getMercadoPagoRoutes()
    }

    fun getInstallments(bin: String, amount: String) : Call<JsonArray>? {
        return mercadoPagoRoutes?.getInstallments(bin,amount)
    }

    fun createCardToken(mercadoPagoCardTokenBody: MercadoPagoCardTokenBody): Call<JsonObject>?{
        return mercadoPagoRoutes?.createCardToken(mercadoPagoCardTokenBody)
    }
}