package com.example.mydatasiswa.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mydatasiswa.modeldata.DataSiswa
import com.example.mydatasiswa.repositori.RepositoryDataSiswa
import com.example.mydatasiswa.uicontroller.route.DestinasiDetail
import kotlinx.coroutines.launch

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

// Sealed interface untuk merepresentasikan status UI
sealed interface StatusUiDetail {
    object Loading : StatusUiDetail
    data class Success(val satusiswa: DataSiswa) : StatusUiDetail
    object Error : StatusUiDetail
}

class DetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriSiswa: RepositoryDataSiswa
) : ViewModel() {

    private val idSiswa: Int = checkNotNull(savedStateHandle[DestinasiDetail.itemIdArg])

    var statusUiDetail: StatusUiDetail by mutableStateOf(StatusUiDetail.Loading)
private set
    init {
        getSatuSiswa()
    }

    // Fungsi untuk mengambil data siswa dari repositori
    fun getSatuSiswa() {
        viewModelScope.launch {
            statusUiDetail = StatusUiDetail.Loading
            statusUiDetail = try {
                StatusUiDetail.Success(satusiswa = repositoriSiswa.getStatusSiswa(idSiswa))
            } catch (e: HttpException) {
                StatusUiDetail.Error
            } catch (e: IOException) {
                StatusUiDetail.Error
            }
        }
    }

    // Fungsi untuk menghapus satu data siswa
    fun hapusSatuSiswa() {
        viewModelScope.launch {
            try {
                // Panggil fungsi hapus dari repositori
                val response = repositoriSiswa.deleteDataSiswa(idSiswa)
                if (response.isSuccessful) {
                    println("Data berhasil dihapus: ${response.message()}")
                } else {
                    println("Data gagal dihapus: ${response.message()}")
                }
            } catch (e: Exception) {
                println("Terjadi kesalahan saat menghapus data: ${e.message}")
            }
        }
    }
}