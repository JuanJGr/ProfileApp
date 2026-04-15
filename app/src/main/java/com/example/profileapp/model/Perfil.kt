package com.example.profileapp.model

data class Perfil(
    val nombre: String,
    val edad: Int,
    val ciudad: String,
    val correo: String,
    val programa: String,
    val semestre: Int,
    val descripcion: String,
    val hobbies: List<String>,
    val pasatiempos: List<String>,
    val deportes: List<String>,
    val intereses: List<String>
)