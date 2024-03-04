package com.example.droidsum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.droidsum.modelo.LoginViewModel
import com.example.droidsum.modelo.ViewModelFactory
import com.example.droidsum.network.SumService
import com.example.droidsum.ui.theme.DroidSumTheme
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : ComponentActivity() {
    private val loginViewModel: LoginViewModel by viewModels { ViewModelFactory(servicio) }
    private lateinit var servicio: SumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your_base_url_here")
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        servicio = retrofit.create(SumService::class.java)
        setContent {
            DroidSumTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(loginViewModel)
                }
            }
        }
    }
}

