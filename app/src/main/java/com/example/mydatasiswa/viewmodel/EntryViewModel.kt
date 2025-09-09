package com.example.mydatasiswa.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mydatasiswa.modeldata.toDataSiswa
import com.example.mydatasiswa.modeldata.DetailSiswa
import com.example.mydatasiswa.modeldata.UIStateSiswa
import com.example.mydatasiswa.repositori.RepositoryDataSiswa
import retrofit2.Response

class EntryViewModel(private val repositoryDataSiswa: RepositoryDataSiswa): ViewModel() {
    var uiStateSiswa by mutableStateOf(UIStateSiswa())
        private  set

    /* fungsi untuk memvalidasi input*/
    private fun validasiInput(uiState: DetailSiswa = uiStateSiswa.detailSiswa): Boolean {
        return  with (uiState) {
            nama.isNotBlank() && alamat.isNotBlank() && telpon.isNotBlank()
        }
    }

    fun updateUiState(detailSiswa: DetailSiswa) {
        uiStateSiswa =
            UIStateSiswa(detailSiswa = detailSiswa, isEntryValid = validasiInput(detailSiswa))
    }

    suspend fun addSiswa(){
        if (validasiInput()) {
            val sip:Response<Void> = repositoryDataSiswa.postDataSiswa(uiStateSiswa.detailSiswa.toDataSiswa())
            if (sip.isSuccessful){
                println("Sukses Tambahkan Data : ${sip.message()}")
            }else{
                println("Gagal Tambahkan Data : ${sip.errorBody()}")
            }
        }
    }

}