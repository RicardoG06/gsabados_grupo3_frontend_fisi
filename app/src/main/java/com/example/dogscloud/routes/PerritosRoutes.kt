package com.example.dogscloud.routes

import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.models.Perritos
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface PerritosRoutes {

    @Multipart
    @POST("perritos/create")
    fun create(
        @Part image: MultipartBody.Part,
        @Part ("perrito") perrito: RequestBody):
            Call<ResponseHttp>
}