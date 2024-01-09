package com.example.roomsiswa.repositori

import com.example.roomsiswa.data.Barang
import com.example.roomsiswa.data.BarangDao
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriBarang(private val barangDao: BarangDao) : RepositoriBarang {

    override fun getAllBarangStream(): Flow<List<Barang>> = barangDao.getAllBarang()

    override fun getBarangStream(id: Int): Flow<Barang?> { return barangDao.getBarang(id) }

    override suspend fun insertBarang(barang: Barang) = barangDao.insert(barang)

    override suspend fun deleteBarang(barang: Barang) = barangDao.delete(barang)

    override suspend fun updateBarang(barang: Barang) { barangDao.update(barang) }
}