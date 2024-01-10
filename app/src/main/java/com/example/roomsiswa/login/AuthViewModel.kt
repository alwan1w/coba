package com.example.roomsiswa.login

import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    // Data pengguna (contoh sederhana)
    private val userCredentials = mapOf(
        "1" to "1",
        "user2" to "password2"
        // Tambahkan data pengguna lainnya
    )

    fun authenticate(username: String, password: String): Boolean {
        // Periksa apakah username dan password cocok
        return userCredentials[username] == password
    }
}