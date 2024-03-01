@file:Suppress("DEPRECATION")

package com.example.droidsum.network

import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object Cliente {
    private const val BASE_URL = "http://sicenet.surguanajuato.tecnm.mx"
    fun createService(): SumService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        return retrofit.create(SumService::class.java)
    }
}