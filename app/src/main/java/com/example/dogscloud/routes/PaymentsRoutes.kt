package com.example.dogscloud.routes

import com.example.dogscloud.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PaymentsRoutes {

    @POST("payments/create")
    fun createPayment(
        @Body mercadoPagoPayment: MercadoPagoPayment
    ): Call<ResponseHttp>

}