package com.example.dogscloud.api

import com.example.dogscloud.routes.PerritosRoutes
import com.example.dogscloud.routes.TrabajadoresRoutes
import com.example.dogscloud.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://192.168.1.73:3000/api/"
    val retrofit = RetrofitClient()

    fun getUserRoutes(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }

    fun getPerritosrRoutes(): PerritosRoutes{
        return retrofit.getClient(API_URL).create(PerritosRoutes::class.java)
    }

    fun getTrabajadoresRoutes(): TrabajadoresRoutes{
        return retrofit.getClient(API_URL).create(TrabajadoresRoutes::class.java)
    }


}