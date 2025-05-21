package org.example.project.data

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val info: PageInfo,
    val results: List<Character>
)

@Serializable
data class PageInfo(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val image: String
)