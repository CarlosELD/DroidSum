package com.example.droidsum

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun PerfilScreen(
    navController: NavHostController,
    nombreEstudiante: String,
    nombreCarrera: String,
    semestre: String,
    promedioGeneral: Float
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Muestra el nombre del estudiante
        Text(
            text = "Nombre del Estudiante: $nombreEstudiante",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Muestra el nombre de la carrera
        Text(
            text = "Carrera: $nombreCarrera",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Muestra el semestre
        Text(
            text = "Semestre: $semestre",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Muestra el promedio general
        Text(
            text = "Promedio General: $promedioGeneral",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Botón para regresar al inicio de sesión
        Button(
            onClick = {
                navController.popBackStack()
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Regresar")
        }
    }
}



