package com.example.mydatasiswa.apiservice

import com.example.mydatasiswa.modeldata.DataSiswa
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface ServiceApiSiswa {@GET("bacateman.php")
suspend fun getSiswa(): List<DataSiswa>

    @POST("insertTM.php")
    suspend fun postDataSiswa(@Body dataSiswa: DataSiswa): retrofit2.Response<Void>

    @GET("baca1teman.php/{id}")
    suspend fun getStatusSiswa(@Query("id") id: Int): DataSiswa

    @PUT("update.php/{id}")
    suspend fun editDataSiswa(@Query("id")id: Int,@Body dataSiswa: DataSiswa): retrofit2.Response<Void>

    @DELETE("delete.php/{id}")
    suspend fun deleteDataSiswa(@Query("id") id: Int): retrofit2.Response<Void>



}