package com.example.dogscloud.api

import com.example.dogscloud.routes.UsersRoutes

class ApiRoutes {

    val API_URL = "http://192.168.1.73:3000/api/"
    val retrofit = RetrofitClient()

    fun getUserRoutes(): UsersRoutes {
        return retrofit.getClient(API_URL).create(UsersRoutes::class.java)
    }
}