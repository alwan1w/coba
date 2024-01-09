package com.example.roomsiswa.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsiswa.repositori.RepositoriBarang
import com.example.roomsiswa.ui.halaman.ItemEditDestination
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class EditViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriBarang: RepositoriBarang
) : ViewModel() {

    var barangUiState by mutableStateOf(UIStateBarang())
        private set

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            barangUiState = repositoriBarang.getBarangStream(itemId)
                .filterNotNull()
                .first()
                .toUiStateBarang(true)
        }
    }

    suspend fun updateBarang() {
        if (validasiInput(barangUiState.detailBarang)) {
            repositoriBarang.updateBarang(barangUiState.detailBarang.toBarang())
        }
        else {
            println("Data tidak valid")
        }
    }

    fun updateUiState(detailBarang: DetailBarang) {
        barangUiState =
            UIStateBarang(detailBarang = detailBarang, isEntryValid = validasiInput(detailBarang))
    }

    private fun validasiInput(uiState: DetailBarang = barangUiState.detailBarang ): Boolean {
        return with(uiState) {
            nama.isNotBlank() && kode.isNotBlank() && jumlah.isNotBlank() && harga.isNotBlank()
        }
    }


}