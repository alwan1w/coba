package com.example.roomsiswa.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Barang::class], version = 1, exportSchema = false)
abstract class DatabaseBarang : RoomDatabase() {
    abstract fun barangDao(): BarangDao

    companion object {
        @Volatile
        private var Instance: DatabaseBarang? = null

        fun getDatabase(context: Context): DatabaseBarang {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseBarang::class.java,
                    "barang_database"
                )
                    .build().also { Instance = it }
            })
        }
    }
}