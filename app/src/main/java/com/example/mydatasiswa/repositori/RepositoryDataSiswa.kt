package com.example.mydatasiswa.repositori

import com.example.mydatasiswa.apiservice.ServiceApiSiswa
import com.example.mydatasiswa.modeldata.DataSiswa

interface RepositoryDataSiswa {
    suspend fun getSiswa(): List<DataSiswa>
    suspend fun postDataSiswa(dataSiswa: DataSiswa) : retrofit2.Response<Void>
    suspend fun getStatusSiswa(id: Int) : DataSiswa
    suspend fun editDataSiswa(id:Int, dataSiswa: DataSiswa) : retrofit2.Response<Void>
    suspend fun deleteDataSiswa(id: Int) : retrofit2.Response<Void>
}

class JaringanRepositoryDataSiswa(
    private val serviceApiSiswa: ServiceApiSiswa
): RepositoryDataSiswa{
    override suspend fun getSiswa(): List<DataSiswa> = serviceApiSiswa.getSiswa()
    override suspend fun postDataSiswa(dataSiswa: DataSiswa): retrofit2.Response<Void> = serviceApiSiswa.postDataSiswa(dataSiswa)
    override suspend fun getStatusSiswa(id: Int): DataSiswa = serviceApiSiswa.getStatusSiswa(id)
    override suspend fun editDataSiswa(id: Int,dataSiswa: DataSiswa): retrofit2.Response<Void> = serviceApiSiswa.editDataSiswa(id, dataSiswa)
    override suspend fun deleteDataSiswa(id: Int): retrofit2.Response<Void> = serviceApiSiswa.deleteDataSiswa(id)
}