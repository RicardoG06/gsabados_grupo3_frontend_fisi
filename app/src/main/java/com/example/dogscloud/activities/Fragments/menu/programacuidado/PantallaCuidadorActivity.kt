package com.example.dogscloud.activities.Fragments.menu.programacuidado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dogscloud.R
import com.example.dogscloud.activities.Fragments.menu.programapaseo.PaseadorEscogido
import com.example.dogscloud.activities.MyToolbar
import com.example.dogscloud.adapters.TrabajadoresAdapter
import com.example.dogscloud.models.Trabajadores
import com.example.dogscloud.models.User
import com.example.dogscloud.providers.TrabajadoresProvider
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PantallaCuidadorActivity : AppCompatActivity() {

    var recyclerViewTrabajadores: RecyclerView? = null
    var adapter: TrabajadoresAdapter? = null
    var user : User? = null
    var trabajadorProvider: TrabajadoresProvider? = null
    var trabajadores: ArrayList<Trabajadores> = ArrayList()

    var id_rol : Int? = null

    var buttonGoTocuidador: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_cuidador)

        id_rol = intent.getIntExtra("id_rol", 2)

        MyToolbar().show(this,"Cuidadores",true)

        buttonGoTocuidador = findViewById(R.id.imageButton)
        trabajadorProvider = TrabajadoresProvider()

        recyclerViewTrabajadores = findViewById(R.id.recyclewViewTrabajadores)
        recyclerViewTrabajadores?.layoutManager = LinearLayoutManager(this)

        getTrabajadorCuidador()
    }

    private fun getTrabajadorCuidador(){
        trabajadorProvider?.findbyrol(id_rol!!)?.enqueue(object: Callback<ArrayList<Trabajadores>>{
            override fun onResponse(
                call: Call<ArrayList<Trabajadores>>,
                response: Response<ArrayList<Trabajadores>>
            ) {
                if(response.body() != null){
                    trabajadores = response.body()!!
                    adapter = TrabajadoresAdapter(this@PantallaCuidadorActivity , trabajadores)
                    recyclerViewTrabajadores?.adapter = adapter
                }
            }

            override fun onFailure(call: Call<ArrayList<Trabajadores>>, t: Throwable) {
                Toast.makeText(this@PantallaCuidadorActivity , t.message , Toast.LENGTH_LONG).show()
                Log.d("PantallaCuidadorActivity", "Error: ${t.message}"  )
            }

        })
    }


}
