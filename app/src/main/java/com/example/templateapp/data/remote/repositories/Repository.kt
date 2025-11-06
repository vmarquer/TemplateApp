package com.example.templateapp.data.remote.repositories

import com.example.templateapp.data.remote.api.ApiService
import javax.inject.Inject

interface RepositoryInterface {
    suspend fun getHelloWorld()
}

class Repository @Inject constructor(
    private val apiService: ApiService
) : RepositoryInterface {

    override suspend fun getHelloWorld() {
        try {
            val response = apiService.getHellowWorld()
            if (response.isSuccessful) {
                response.body()?.let { result ->
                    print(result)
                } ?: run {
                    throw Error("Internal error")
                }
            } else {
                when (response.code()) {
                    401 -> throw Error("Unauthorized request")
                    403 -> throw Error("Forbidden request")
                    404 -> throw Error("Ressource not found")
                    503 -> throw Error("Unauthorized request")
                    else -> throw Error("Internal error")
                }
            }

        } catch (e: Exception) {
            when (e) {
                is java.net.UnknownHostException -> throw Error("No internet")
                is java.net.SocketTimeoutException -> throw Error("Timeout")
                else -> throw Error("Unknown error")
            }
        }
    }
}

