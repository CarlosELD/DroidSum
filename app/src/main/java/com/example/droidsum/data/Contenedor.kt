package com.example.droidsum.data

import android.content.Context
import com.example.droidsum.network.SumLoginService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import kotlinx.serialization.json.Json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
interface Base {
    val snRepository: SumRepository
}
@Suppress("DEPRECATION")
class Contenedor(applicationContext: Context) : Base {
    private val baseUrl = "https://android-kotlin-fun-mars-server.appspot.com/"
    private val baseUrlSN = "https://sicenet.surguanajuato.tecnm.mx"
    private var client: OkHttpClient = OkHttpClient()
    init {
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(AgregarCookie(applicationContext)) // VERY VERY IMPORTANT
        builder.addInterceptor(RecibirCookie(applicationContext)) // VERY VERY IMPORTANT
        client = builder.build()
    }
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .build()
    private val retrofitSN: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrlSN)
        .addConverterFactory(SimpleXmlConverterFactory.createNonStrict())
        .client(client)
        .build()
    //bodyacceso.toRequestBody("text/xml; charset=utf-8".toMediaType())
    private val retrofitServiceSN: SumLoginService by lazy {
        retrofitSN.create(SumLoginService::class.java)
    }
    override val snRepository: NetworSNRepository by lazy {
        NetworSNRepository(retrofitServiceSN)
    }
}