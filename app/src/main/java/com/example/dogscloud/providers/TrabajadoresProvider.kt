package com.example.dogscloud.providers

import com.example.dogscloud.api.ApiRoutes
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.routes.TrabajadoresRoutes
import com.example.dogscloud.routes.UsersRoutes
import retrofit2.Call

class TrabajadoresProvider {

    private var TrabajadoresRoutes: TrabajadoresRoutes? = null
    init {
        val api = ApiRoutes()
        TrabajadoresRoutes = api.getTrabajadoresRoutes()

    }

    fun findbyrol(id_rol: Int): Call<ArrayList<Trabajadores>>?{
        return TrabajadoresRoutes?.findByrol(id_rol)
    }
}