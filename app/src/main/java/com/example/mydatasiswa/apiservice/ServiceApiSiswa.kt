package com.example.mydatasiswa.apiservice

import com.example.mydatasiswa.modeldata.DataSiswa
import retrofit2.http.GET

interface ServiceApiSiswa {
    @GET("bacateman.php")
    suspend fun  getSiswa() : List<DataSiswa>
}