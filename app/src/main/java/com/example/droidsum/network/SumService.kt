package com.example.droidsum.network

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SumService {
    @Headers("Content-Type: text/xml", "SOAPAction: \"http://tempuri.org/accesoLogin\"")
    @POST("/ws/wsalumnos.asmx")
    fun login(@Body request: LoginRequest?): Call<LoginResponse?>?

    @Headers(
        "Content-Type: text/xml",
        "SOAPAction: \"http://tempuri.org/getAlumnoAcademicoWithLineamiento\""
    )
    @POST("/ws/wsalumnos.asmx")
    fun getProfile(@Body request: ProfileRequest?): Call<ProfileResponse?>?

    @Headers("Content-Type: text/xml", "SOAPAction: \"http://tempuri.org/getPerfil\"")
    @POST("/ws/wsalumnos.asmx")
    fun accessProfile(@Body request: AccessProfile?): Call<AccessProfile?>?
}