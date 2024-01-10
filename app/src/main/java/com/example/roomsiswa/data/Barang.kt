package com.example.roomsiswa.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "tblBarang")
data class Barang(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val nama : String,
    val kode : String,
    val jumlah: String,
    val harga : String,
    val tanggal :  String,
)
