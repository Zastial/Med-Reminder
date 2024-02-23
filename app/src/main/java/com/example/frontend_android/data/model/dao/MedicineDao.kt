package com.example.frontend_android.data.model.dao

import com.example.frontend_android.data.model.entities.Medicine
import com.example.frontend_android.data.model.entities.Substance
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MedicineDao {

    @GET("medicines")
    fun getMedicines(@Query("search") search: String? = null): Call<List<Medicine>>

    @GET("medicines/substances")
    fun getSubstances(@Query("search") search: String? = null): Call<List<Substance>>

    @GET("medicines/{cis}")
    fun getMedicine(@Path("cis") cis: Long): Call<Medicine>

    @POST("medicines")
    fun insertMedicine(@Body() prescription : Medicine): Call<Medicine>

    @DELETE("medicines/{id}")
    fun deleteMedicine(@Path("id") id: Long): Call<Medicine>

}