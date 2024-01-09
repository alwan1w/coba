package com.example.roomsiswa.repositori

import android.content.Context
import com.example.roomsiswa.data.DatabaseBarang


interface ContainerApp {
    val repositoriBarang : RepositoriBarang
}

class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriBarang: RepositoriBarang by lazy {
        OfflineRepositoriBarang(DatabaseBarang.getDatabase(context).barangDao())
    }
}