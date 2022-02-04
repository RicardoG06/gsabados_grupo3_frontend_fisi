package com.example.dogscloud.activities.Fragments.mispedidos.installments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.example.dogscloud.R
import com.example.dogscloud.models.*
import com.example.dogscloud.providers.MercadoPagoProvider
import com.example.dogscloud.providers.PaymentsProvider
import com.example.dogscloud.utils.SharedPref
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ClientPaymentsInstallmentsActivity : AppCompatActivity() {

    val TAG = "ClientPaymentsInst"
    var textViewTotal : TextView? = null
    var textViewInstallmentDescription: TextView? = null
    var buttonPay: Button? = null
    var spinnerInstallments : Spinner? = null

    var mercadoPagoProvider = MercadoPagoProvider()
    var paymentsProvider : PaymentsProvider? = null
    var user: User? = null

    var cardToken = ""
    var firstSixDigits = ""

    var sharedPref: SharedPref? = null
    var selectedProducts = ArrayList<CarritoCompra>()
    var gson = Gson()

    var total = 0.0

    var installmentsSelected = ""

    var PaymentMethodId = ""
    var PaymentTypeId = ""
    var issuerId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_client_payments_installments)

        sharedPref = SharedPref(this)

        getUserFromSession()

        paymentsProvider = PaymentsProvider()

        cardToken = intent.getStringExtra("cardToken").toString()
        firstSixDigits = intent.getStringExtra("firstSixDigits").toString()

        textViewTotal = findViewById(R.id.textview_total)
        textViewInstallmentDescription = findViewById(R.id.textview_installments_descripcion)
        buttonPay = findViewById(R.id.btn_pay)
        spinnerInstallments = findViewById(R.id.spinner_installments)

        buttonPay?.setOnClickListener{
            if(!installmentsSelected.isNullOrBlank()){
                createPayment()
            }
            else{
                Toast.makeText(this, "Debes seleccionar el numero de cuotas", Toast.LENGTH_SHORT).show()
            }
        }

        getProductsFromSharedPref()
        getInstallments()
    }

    private fun createPayment(){

        val order = Order(
            id_user = user?.id!!,
            compra = selectedProducts
        )

        val payer = Payer(email = user?.email!!)

        val mercadoPagoPayment = MercadoPagoPayment(
            order = order,
            token = cardToken,
            description = "Dogs in Cloud app",
            paymentMethodId = PaymentMethodId,
            paymentTypeId = PaymentTypeId,
            issuerId = issuerId,
            payer = payer,
            transactionAmount = total,
            installments = installmentsSelected.toInt()
        )
        paymentsProvider?.create(mercadoPagoPayment)?.enqueue(object : Callback<ResponseHttp>{
            override fun onResponse(call: Call<ResponseHttp>, response: Response<ResponseHttp>) {
                if(response.body() != null){
                    Log.d(TAG, "Response: $response")
                    Log.d(TAG, "Body: ${response.body()}")
                    Toast.makeText(this@ClientPaymentsInstallmentsActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                }
                else{
                    Toast.makeText(this@ClientPaymentsInstallmentsActivity, response.body()?.message, Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseHttp>, t: Throwable) {
                Toast.makeText(this@ClientPaymentsInstallmentsActivity, "No hubo una respuesta exitosa", Toast.LENGTH_LONG).show()
            }

        })
    }

    private fun getInstallments(){
        mercadoPagoProvider.getInstallments(firstSixDigits,"$total")?.enqueue(object: Callback<JsonArray>{
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                if (response.body() != null){
                    val jsoninstallments = response.body()!!.get(0).asJsonObject.get("payer_costs").asJsonArray

                    val type = object: TypeToken<ArrayList<MercadoPagoInstallments>>(){}.type
                    val installments = gson.fromJson<ArrayList<MercadoPagoInstallments>>(jsoninstallments, type)

                    PaymentMethodId = response.body()?.get(0)?.asJsonObject?.get("payment_method_id")?.asString!!
                    PaymentTypeId = response.body()?.get(0)?.asJsonObject?.get("payment_type_id")?.asString!!
                    issuerId = response.body()?.get(0)?.asJsonObject?.get("issuer")?.asJsonObject?.get("id")?.asString!!

                    Log.d(TAG , "Response : $response")
                    Log.d(TAG , "installments : $installments")

                    val arrayAdapter = ArrayAdapter<MercadoPagoInstallments>(this@ClientPaymentsInstallmentsActivity , android.R.layout.simple_dropdown_item_1line , installments)
                    spinnerInstallments?.adapter = arrayAdapter
                    spinnerInstallments?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                        override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, l: Long) {
                            installmentsSelected = "${installments[position].installments}"
                            Log.d(TAG , "Cuotas Seleccionadas: $installmentsSelected")
                            textViewInstallmentDescription?.text = installments[position].recommendedMessage
                        }

                        override fun onNothingSelected(p0: AdapterView<*>?) {

                        }

                    }
                }
            }

            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                Toast.makeText(this@ClientPaymentsInstallmentsActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }


        })
    }

    private fun getProductsFromSharedPref(){
        if(!sharedPref?.getData("order").isNullOrBlank()){
            val type = object : TypeToken<ArrayList<CarritoCompra>>() {}.type
            selectedProducts = gson.fromJson(sharedPref?.getData("order"),type)

            for(p in selectedProducts){
                total = total + p.precio_total!!.toInt()
            }

            textViewTotal?.text = "S/. ${total}"
        }
    }

    private fun getUserFromSession(){

        val gson = Gson()

        if(!sharedPref?.getData("user").isNullOrBlank()){
            // si el usuario existe en sesion
            user = gson.fromJson(sharedPref?.getData("user"), User::class.java)
            Log.d("MainActivity", "Usuario: $user")
        }
    }
}