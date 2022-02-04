package com.example.dogscloud.providers

import android.app.DownloadManager
import com.example.dogscloud.api.ApiRoutes
import com.example.dogscloud.models.CarritoCompra
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.routes.OrdersRoutes
import com.example.dogscloud.routes.UsersRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class OrdersProvider {
    private var ordersRoutes: OrdersRoutes? = null
    init {
        val api = ApiRoutes()
        ordersRoutes = api.getOrdersRoutes()
    }

    fun create(order: CarritoCompra): Call<ResponseHttp>?{
        return ordersRoutes?.create(order)
    }

    fun getOrdersByStatus(status: String): Call<ArrayList<CarritoCompra>>?{
        return ordersRoutes?.getOrdersByStatus(status)
    }

    fun getOrdersByClientAndStatus(id_user : String , status: String): Call<ArrayList<CarritoCompra>>?{
        return ordersRoutes?.getOrdersByClientAndStatus(id_user , status)
    }

}