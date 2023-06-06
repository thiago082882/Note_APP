package com.example.notes.models

data class User(
    val createdAt: String,
    val email: String,
    val id: String,
    val password: String,
    val updatedAt: String,
    val username: String,
    val v: Int
)