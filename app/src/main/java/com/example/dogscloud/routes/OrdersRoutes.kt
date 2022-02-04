package com.example.dogscloud.routes

import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.models.Perritos
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface OrdersRoutes {

    @GET("orders/findByStatus/{status}")
    fun getOrdersByStatus(
        @Path("status") status : String,
    ): Call<ArrayList<CarritoCompra>>

    @GET("orders/findByClientAndStatus/{id_user}/{status}")
    fun getOrdersByClientAndStatus(
        @Path("id_user") id_user : String,
        @Path("status") status : String
    ): Call<ArrayList<CarritoCompra>>

    @POST("orders/create")
    fun create(
        @Body order: CarritoCompra): Call<ResponseHttp>

}