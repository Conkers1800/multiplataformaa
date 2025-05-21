package org.example.project.network

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.example.project.data.ApiResponse
import org.example.project.data.Character

object ApiClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    suspend fun fetchCharacters(): List<Character> {
        val response: ApiResponse =
            client.get("https://rickandmortyapi.com/api/character").body()
        return response.results
    }
}