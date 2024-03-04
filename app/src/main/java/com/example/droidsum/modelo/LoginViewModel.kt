package com.example.droidsum.modelo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.droidsum.network.LoginRequest
import com.example.droidsum.network.LoginResponse
import com.example.droidsum.network.ProfileRequest
import com.example.droidsum.network.ProfileResponse
import com.example.droidsum.network.SumService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel(private val sicenetService: SumService) : ViewModel() {

    fun authenticate(matricula: String, password: String, onResult: (Boolean) -> Unit) {
        val request = LoginRequest().apply {
            this.body?.accesoLogin?.strMatricula = matricula
            this.body?.accesoLogin?.strContrasenia = password
            this.body?.accesoLogin?.tipoUsuario = "ALUMNO"
        }

        sicenetService.login(request).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    // Aquí puedes verificar si la autenticación fue exitosa
                    // Por ejemplo, puedes examinar la respuesta para determinar si el usuario fue autenticado correctamente
                    onResult(true)
                } else {
                    // Si la respuesta no fue exitosa, retorna false
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Si la llamada falla, retorna false
                onResult(false)
            }
        })
    }

    fun getProfileData(matricula: String, onProfileData: (ProfileRequest.GetAlumnoAcademicoWithLineamiento?) -> Unit) {
        val request = ProfileRequest().apply {
            this.body?.getAlumnoAcademicoWithLineamiento?.matricula = matricula
        }

        sicenetService.getProfile(request).enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(call: Call<ProfileResponse>, response: Response<ProfileResponse>) {
                if (response.isSuccessful) {
                    val profileResponse = response.body()
                    val profileData = profileResponse?.body?.getAlumnoAcademicoWithLineamientoResponse
                    onProfileData(profileData)
                } else {
                    onProfileData(null)
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                onProfileData(null)
            }
        })
    }
}

class ViewModelFactory(private val servicio: SumService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(servicio) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}