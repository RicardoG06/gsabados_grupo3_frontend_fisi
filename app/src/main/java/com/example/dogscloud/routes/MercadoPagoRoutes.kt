package com.example.dogscloud.routes

import com.example.dogscloud.models.MercadoPagoCardTokenBody
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface MercadoPagoRoutes {

    @GET("v1/payment_methods/installments?access_token=TEST-2618165342880021-020407-aeb3404942be56b4f36492499d0c95ec-1068540677")
    fun getInstallments(@Query("bin") bin : String , @Query("amount") amount : String): Call<JsonArray>

    @POST("v1/card_tokens?public_key=TEST-f2a7218e-67cb-4e7a-a431-02c0c429ed27")
    fun createCardToken(@Body body: MercadoPagoCardTokenBody): Call<JsonObject>
}