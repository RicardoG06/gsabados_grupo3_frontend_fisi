package com.example.dogscloud.routes

import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.models.Perritos
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface UsersRoutes {
    @POST("users/create")
    fun register(@Body user: User): Call<ResponseHttp>

    @FormUrlEncoded
    @POST("users/login")
    fun login(@Field("email") email: String, @Field("password") password: String): Call<ResponseHttp>

    @FormUrlEncoded
    @POST("users/validacion")
    fun loginVali(@Field("email") email: String?): Call<ResponseHttp>

    @Multipart
    @PUT("users/update")
    fun update(
        @Part image: MultipartBody.Part,
        @Part( "user") user: RequestBody
    ): Call<ResponseHttp>
}