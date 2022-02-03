package com.example.dogscloud.routes

import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.models.Trabajadores
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface TrabajadoresRoutes {

    @GET("trabajador/getAll")
    fun TraerTrabajadores(): Call<ArrayList<Trabajadores>>

    @GET("trabajador/findByrol/{id_rol}")
    fun findByrol(
        @Path("id_rol") id_rol: Int,
    ): Call<ArrayList<Trabajadores>>
}