package com.example.droidsum.navegation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.droidsum.LoginScreen
import com.example.droidsum.PerfilScreen
import com.example.droidsum.modelo.LoginViewModel

@Composable
fun Navegacion(loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    val context = LocalContext.current
    NavHost(navController, startDestination = "login") {
        composable("login") {
            LoginScreen(viewModel = loginViewModel, navController = NavController(context = context))
        }
        composable(
            "perfil/{nombreEstudiante}/{nombreCarrera}/{semestre}/{promedioGeneral}",
            arguments = listOf(
                navArgument("nombreEstudiante") { type = NavType.StringType },
                navArgument("nombreCarrera") { type = NavType.StringType },
                navArgument("semestre") { type = NavType.StringType },
                navArgument("promedioGeneral") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val nombreEstudiante = backStackEntry.arguments?.getString("nombreEstudiante") ?: ""
            val nombreCarrera = backStackEntry.arguments?.getString("nombreCarrera") ?: ""
            val semestre = backStackEntry.arguments?.getString("semestre") ?: ""
            val promedioGeneral = backStackEntry.arguments?.getFloat("promedioGeneral") ?: 0f
            PerfilScreen(
                navController = navController,
                nombreEstudiante = nombreEstudiante,
                nombreCarrera = nombreCarrera,
                semestre = semestre,
                promedioGeneral = promedioGeneral
            )
        }
    }
}