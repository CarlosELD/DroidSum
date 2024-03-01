package com.example.droidsum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.droidsum.modelo.LoginViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val showError = remember { mutableStateOf(false) }
    val navigateToProfile = remember { mutableStateOf(false) }
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TextField(
                value = username.value,
                onValueChange = { username.value = it },
                label = { Text("Usuario") }
            )
            Spacer(Modifier.padding(10.dp))
            TextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña") }
            )
            if (showError.value) {
                Text(
                    text = "Usuario o contraseña incorrectos, inténtalo de nuevo",
                    color = MaterialTheme.colorScheme.error
                )
            }
            Button(
                onClick = {
                    val isAuthenticated = viewModel.authenticate(username.value, password.value)
                    if (isAuthenticated) {
                        navigateToProfile.value = true
                    } else {
                        showError.value = true
                    }
                },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Iniciar sesión")
            }
        }
    }
}

@Preview
@Composable
fun Preview(){
    LoginScreen(viewModel = LoginViewModel())
}

