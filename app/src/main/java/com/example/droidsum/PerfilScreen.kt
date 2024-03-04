package com.example.droidsum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.droidsum.network.ProfileRequest

@Composable
fun PerfilScreen(profile: ProfileRequest.GetAlumnoAcademicoWithLineamiento) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido ${profile.nombreCompleto}", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Matrícula: ${profile.numeroControl}", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Semestre: ${profile.semestre}", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Carrera: ${profile.carrera}", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Tipo de usuario: ${profile.tipoUsuario}", fontSize = 16.sp)

        // Mostrar las calificaciones
        if (!profile.calificaciones.isNullOrEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text("Calificaciones:", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            profile.calificaciones?.forEach { calificacion ->
                Spacer(modifier = Modifier.height(8.dp))
                Text("${calificacion.materia}: ${calificacion.calificacion}", fontSize = 16.sp)
            }
        }
    }
}