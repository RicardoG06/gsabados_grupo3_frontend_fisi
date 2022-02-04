package com.example.dogscloud.models

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

class CarritoCompra(
    @SerializedName("id_orden") var id_orden: String? = null,
    @SerializedName("id_user") var id_user: String? = null,
    @SerializedName("nombre_cuidador") var nombre_cuidador: String? = null,
    @SerializedName("popularidad_cuidador") var popularidad_cuidador: String? = null,
    @SerializedName("precio_x_hora_cuidador") var precio_x_hora_cuidador: String? = null,
    @SerializedName("precio_x_hora_paseador") var precio_x_hora_paseador: String? = null,
    @SerializedName("imagen_trabajador") var imagen_trabajador: String? = null,
    @SerializedName("precio_total") var precio_total: String? = null,
    @SerializedName("nombre_perrito") var nombre_perrito: String? = null,
    @SerializedName("raza_perrito") var raza_perrito: String? = null,
    @SerializedName("fecha_cuidado") var fecha_cuidado: String? = null,
    @SerializedName("hora_inicio") var hora_inicio: String? = null,
    @SerializedName("horas_servicio") var horas_servicio: String? = null,
    @SerializedName("direccion_cliente") var direccion_cliente: String? = null,
    @SerializedName("referencia_cliente") var referencia_cliente: String? = null,
    @SerializedName("tipo_trabajador") var tipo_trabajador: String? = null,
    @SerializedName("timestamp") var timestamp: Long = 0
    ){

    fun toJson(): String{
        return Gson().toJson(this)
    }

    override fun toString(): String {
        return "CarritoCompra(id_orden=$id_orden, id_user=$id_user, nombre_cuidador=$nombre_cuidador, popularidad_cuidador=$popularidad_cuidador, precio_x_hora_cuidador=$precio_x_hora_cuidador, precio_x_hora_paseador=$precio_x_hora_paseador, imagen_trabajador=$imagen_trabajador, precio_total=$precio_total, nombre_perrito=$nombre_perrito, raza_perrito=$raza_perrito, fecha_cuidado=$fecha_cuidado, hora_inicio=$hora_inicio, horas_servicio=$horas_servicio, direccion_cliente=$direccion_cliente, referencia_cliente=$referencia_cliente, tipo_trabajador=$tipo_trabajador, timestamp=$timestamp)"
    }


}