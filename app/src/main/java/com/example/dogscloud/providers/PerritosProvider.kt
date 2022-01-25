package com.example.dogscloud.providers

import android.app.DownloadManager
import com.example.dogscloud.api.ApiRoutes
import com.example.dogscloud.models.ResponseHttp
import com.example.dogscloud.models.User
import com.example.dogscloud.models.Perritos
import com.example.dogscloud.routes.PerritosRoutes
import com.example.dogscloud.routes.UsersRoutes
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import java.io.File

class PerritosProvider {
    private var PerritosRoutes: PerritosRoutes? = null
    init {
        val api = ApiRoutes()
        PerritosRoutes = api.getPerritosrRoutes()
    }

    fun create(file: File, perrito: Perritos ): Call<ResponseHttp>?{

        val reqFile = RequestBody.create(MediaType.parse("image/*"), file)
        val image = MultipartBody.Part.createFormData("image", file.name , reqFile)
        val requestBody = RequestBody.create(MediaType.parse("text/plain"), perrito.toJson())

        return PerritosRoutes?.create(image, requestBody)

    }
}