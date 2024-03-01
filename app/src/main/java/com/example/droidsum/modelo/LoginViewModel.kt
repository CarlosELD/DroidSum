package com.example.droidsum.modelo

import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    fun authenticate(username: String, password: String): Boolean {
        return (username == "usuario" && password == "contraseña")
    }
}