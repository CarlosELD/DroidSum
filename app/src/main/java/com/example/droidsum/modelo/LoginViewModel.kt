package com.example.droidsum.modelo


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.droidsum.data.SumRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: SumRepository) : ViewModel() {
    // Estado del inicio de sesión
    sealed class LoginState {
        object Idle : LoginState() // Estado inicial
        object Loading : LoginState() // Estado de carga
        data class Success(val profile: String) : LoginState() // Estado de éxito con perfil
        data class Error(val message: String) : LoginState() // Estado de error
    }

    // Estado actual del inicio de sesión
    private val _loginState = mutableStateOf<LoginState>(LoginState.Idle)
    val loginState: State<LoginState> = _loginState

    // Función para iniciar sesión
    fun login(matricula: String, password: String) {
        viewModelScope.launch {
            _loginState.value = LoginState.Loading // Cambio de estado a carga
            try {
                val result = repository.acceso(matricula, password)
                _loginState.value = LoginState.Success(result) // Cambio de estado a éxito con perfil
            } catch (e: Exception) {
                _loginState.value = LoginState.Error("Error al iniciar sesión") // Cambio de estado a error
            }
        }
    }
}