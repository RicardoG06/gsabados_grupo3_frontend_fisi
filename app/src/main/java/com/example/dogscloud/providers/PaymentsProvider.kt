package com.example.dogscloud.providers

import com.example.dogscloud.api.ApiRoutes
import com.example.dogscloud.api.MercadoPagoApiRoutes
import com.example.dogscloud.models.MercadoPagoCardTokenBody
import com.example.dogscloud.models.MercadoPagoPayment
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.routes.MercadoPagoRoutes
import com.example.dogscloud.routes.PaymentsRoutes
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call

class PaymentsProvider {

    var paymentsRoutes: PaymentsRoutes? = null

    init {
        val api = ApiRoutes()
        paymentsRoutes = api.getPaymentsRoutes()
    }

   fun create(mercadoPagoPayment: MercadoPagoPayment): Call<ResponseHttp>?{
       return paymentsRoutes?.createPayment(mercadoPagoPayment)
   }
}