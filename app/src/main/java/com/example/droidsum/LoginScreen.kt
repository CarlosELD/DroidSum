package com.example.droidsum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.droidsum.modelo.LoginViewModel
import com.example.droidsum.network.ProfileRequest

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    var matricula by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }
    var profile by remember { mutableStateOf<ProfileRequest.GetAlumnoAcademicoWithLineamiento?>(null) }
    var isFetchingProfile by remember { mutableStateOf(false) }
    Surface(modifier = Modifier.fillMaxSize(), color = Color.Yellow) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Droid Sum", modifier = Modifier)
            Spacer(modifier = Modifier.padding(70.dp))
            OutlinedTextField(
                value = matricula,
                onValueChange = { matricula = it },
                label = { Text("Matrícula") },
                modifier = Modifier.padding(16.dp)
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Button(
                onClick = {
                    viewModel.authenticate(matricula, password) { isAuthenticated ->
                        if (isAuthenticated) {
                            isFetchingProfile = true
                            viewModel.getProfileData(matricula) { profileData ->
                                profile = profileData
                                isFetchingProfile = false
                            }
                        } else {
                            showError = true
                        }
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Iniciar sesión")
            }
            if (showError) {
                Text(
                    text = "Matrícula o contraseña incorrectos, inténtalo de nuevo",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )
            }
            if (isFetchingProfile) {
                Spacer(modifier = Modifier.height(16.dp))
                CircularProgressIndicator()
            }
            profile?.let {
                Spacer(modifier = Modifier.height(16.dp))
                PerfilScreen(it)
            }
        }
    }
}

