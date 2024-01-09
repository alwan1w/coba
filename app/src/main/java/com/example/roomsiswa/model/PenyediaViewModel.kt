package com.example.roomsiswa.model

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.roomsiswa.AplikasiBarang


object PenyediaViewModel {
    val Factory = viewModelFactory {

        initializer {
            HomeViewModel(aplikasiBarang
                ().container.repositoriBarang
            )
        }

        initializer {
            EntryViewModel(aplikasiBarang
                ().container.repositoriBarang
            )
        }

        initializer {
            DetailsViewModel(
                createSavedStateHandle(),
                aplikasiBarang
                    ().container.repositoriBarang
                ,
            )
        }

        initializer {
            EditViewModel(
                createSavedStateHandle(),
                aplikasiBarang
                    ().container.repositoriBarang
                ,
            )
        }

    }
}

/**
 * Fungsi ekstensi query untuk objek [Application] dan mengembalikan sebuah instance dari
 *  [AplikasiBarang
 *  ].
 */
fun CreationExtras.aplikasiBarang
            (): AplikasiBarang
=
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AplikasiBarang
            )