package com.example.dogscloud.activities.Fragments.menu.trabajadores

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programacuidado.CuidadorEscogido
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.adapters.TrabajadoresAdapterGeneral
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.TrabajadoresProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MisTrabajadores : AppCompatActivity() {

    var recyclerViewTrabajadores: RecyclerView? = null
    var adapter: TrabajadoresAdapterGeneral? = null
    var user : User? = null
    var trabajadorProvider: TrabajadoresProvider? = null
    var trabajadores: ArrayList<Trabajadores> = ArrayList()

    var buttonGoTTrabajador: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_trabajadores)

        MyToolbar().show(this,"Trabajadores",true)

        buttonGoTTrabajador = findViewById(R.id.imageButton)
        trabajadorProvider = TrabajadoresProvider()

        recyclerViewTrabajadores = findViewById(R.id.recyclewViewTrabajadores)
        recyclerViewTrabajadores?.layoutManager = LinearLayoutManager(this)

        getTrabajadorCuidador()
    }

    private fun getTrabajadorCuidador(){
        trabajadorProvider?.TraerTrabajadores()?.enqueue(object: Callback<ArrayList<Trabajadores>> {
            override fun onResponse(
                call: Call<ArrayList<Trabajadores>>,
                response: Response<ArrayList<Trabajadores>>
            ) {
                if(response.body() != null){
                    trabajadores = response.body()!!
                    adapter = TrabajadoresAdapterGeneral(this@MisTrabajadores , trabajadores)
                    recyclerViewTrabajadores?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Trabajadores>>, t: Throwable) {
                Toast.makeText(this@MisTrabajadores , t.message , Toast.LENGTH_LONG).show()
                Log.d("PantallaCuidadorActivity", "Error: ${t.message}"  )
            }

        })
    }

}