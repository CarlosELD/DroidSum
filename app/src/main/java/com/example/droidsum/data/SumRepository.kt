package com.example.droidsum.data

import android.util.Log
import com.example.droidsum.network.SumLoginService
import com.example.droidsum.network.bodyacceso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

interface SumRepository {
    suspend fun acceso(matricula: String, contrasenia: String): String
}
class DataBaseNetRepository():SumRepository {
    override suspend fun acceso(matricula: String, contrasenia: String): String {
        return ""
    }
}
class NetworSNRepository(private val snApiService: SumLoginService) : SumRepository {
    override suspend fun acceso(matricula: String, contrasenia: String): String {
        //callHTTPS()
        val res = snApiService.acceso(bodyacceso.format(matricula,contrasenia).toRequestBody() )
        Log.d("RXML", res.string() )
        return ""
    }
    suspend fun callHTTPS(){
        val matricula = "s20120006"
        val contrasenia = "7g=FY6"
        val tipoUsuario = "ALUMNO"
        val urlString = "https://sicenet.surguanajuato.tecnm.mx/ws/wsalumnos.asmx"
        val soapEnvelope = """
        <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
        xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
          <soap:Body>
            <accesoLogin xmlns="http://tempuri.org/">
              <strMatricula>$matricula</strMatricula>
              <strContrasenia>$contrasenia</strContrasenia>
              <tipoUsuario>$tipoUsuario</tipoUsuario>
            </accesoLogin>
          </soap:Body>
        </soap:Envelope>
    """.trimIndent()
        try {
            val url = URL(urlString)
            val connection = withContext(Dispatchers.IO) { url.openConnection()
            } as HttpsURLConnection
            connection.requestMethod = "POST"
            connection.doOutput = true
            connection.setRequestProperty("Host", "sicenet.surguanajuato.tecnm.mx")
            connection.setRequestProperty("Content-Type", "text/xml; charset=\"UTF-8\"")
            connection.setRequestProperty("Cookie", ".ASPXANONYMOUS=MaWJCZ-" +
                    "X2gEkAAAAODU2ZjkyM2EtNWE3ZC00NTdlLWFhYTAtYjk5ZTE5MDlkODIzeI1pCwvskL6aqtre4eT8Atfq2Po1;")
            connection.setRequestProperty("Content-Length", soapEnvelope.length.toString())
            connection.setRequestProperty("SOAPAction", "\"http://tempuri.org/accesoLogin\"")
            val outputStream: OutputStream = connection.outputStream
            withContext(Dispatchers.IO) { outputStream.write(soapEnvelope.toByteArray(Charsets.UTF_8)) }
            withContext(Dispatchers.IO) { outputStream.close() }
            val responseCode = connection.responseCode
            if (responseCode == HttpURLConnection.HTTP_OK) {
                val cookies = connection.getHeaderField("Set-Cookie")
                val reader = BufferedReader(InputStreamReader(connection.inputStream))
                var line: String?
                val response = StringBuilder()
                while (withContext(Dispatchers.IO) { reader.readLine()
                    }.also { line = it } != null) { response.append(line) }
                println("Respuesta del servicio: $response")
                Log.d("SXML","Respuesta del servicio: $response")
            } else {
                // Manejar errores de conexión
                println("Error en la carga de conexión: $responseCode")
            }
            connection.disconnect() } catch (e: IOException) { e.printStackTrace() }
    }

}