package com.example.roomsiswa.model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsiswa.repositori.RepositoriBarang
import com.example.roomsiswa.ui.halaman.DetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class DetailsViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoriBarang: RepositoriBarang
) : ViewModel() {

    private val barangId: Int = checkNotNull(savedStateHandle[DetailsDestination.barangIdArg])
    val uiState: StateFlow<ItemDetailsUiState> =
        repositoriBarang.getBarangStream(barangId)
            .filterNotNull()
            .map {
                ItemDetailsUiState(detailBarang = it.toDetailBarang())
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemDetailsUiState()
            )

    suspend fun deleteItem() {
        repositoriBarang.deleteBarang(uiState.value.detailBarang.toBarang())
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }


}

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val detailBarang: DetailBarang = DetailBarang(),
)
