package com.example.roomsiswa.repositori

import com.example.roomsiswa.data.Barang
import kotlinx.coroutines.flow.Flow


interface RepositoriBarang {
    fun getAllBarangStream(): Flow<List<Barang>>

    fun getBarangStream(id: Int): Flow<Barang?>

    suspend fun insertBarang(Barang: Barang)

    suspend fun deleteBarang(Barang: Barang)

    suspend fun updateBarang(Barang: Barang)
}