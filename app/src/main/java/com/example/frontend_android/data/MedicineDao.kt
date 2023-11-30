package com.example.frontend_android.data

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.frontend_android.data.model.Medicine
import com.example.frontend_android.data.model.Prescription
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface MedicineDao {

    @GET("medicines")
    fun getMedicines(): Call<List<Medicine>>

    @GET("medicines/{id}")
    fun getMedicine(@Path("id") id: Int): Call<Medicine>

    @POST("medicines")
    fun insertMedicine(@Body() prescription : Medicine): Call<Medicine>

    @DELETE("medicines/{id}")
    fun deleteMedicine(@Path("id") id: Int): Call<Medicine>

}