package com.example.roomsiswa.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.RoomSQLiteQuery
import com.example.roomsiswa.data.Barang
import com.example.roomsiswa.repositori.RepositoriBarang
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(private val repositoriBarang: RepositoriBarang) : ViewModel() {

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    private val _filteredBarangList = MutableStateFlow<List<Barang>>(emptyList())
    val filteredBarangList: StateFlow<List<Barang>> = _filteredBarangList
    val homeUiState: StateFlow<HomeUiState> = repositoriBarang.getAllBarangStream()
        .filterNotNull()
        .map { HomeUiState(listBarang = it.toList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = HomeUiState()
        )
    fun filterBarangList(query: String){
        viewModelScope.launch {
            _filteredBarangList.value = if (query.isBlank()){
                homeUiState.value.listBarang
            } else {
                homeUiState.value.listBarang.filter { it.nama.contains(query, ignoreCase = true) }
            }
        }
    }
}

data class HomeUiState(
    val listBarang: List<Barang> = listOf()
)
