package com.sithuhein.mm.data.utils

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject
import java.lang.Exception

class ResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            422 -> {
                val error = response.body.toString()
                val message = try {
                    JSONObject(error).getString("message")
                } catch (e : Exception) {
                    "Error occur while connecting to server"
                }
                throw Exception(message)
            }

            400 -> {
                val error = response.body.toString()
                val message = try {
                    JSONObject(error).getString("message")
                } catch (e : Exception) {
                    "Resource not found at Server"
                }
                throw Exception(message)
            }

            404 -> { throw Exception("Resource not found at server.")}

            406 -> {
                val error = response.body.toString()
                val message = try {
                    JSONObject(error).getString("message")
                } catch (e : Exception) {
                    "Login again!"
                }
                throw Exception(message)
            }

            500 -> { throw Exception("Internal Server Error")}

            502 -> { throw Exception("Server Under Maintenance")}
        }

        return response
    }
}