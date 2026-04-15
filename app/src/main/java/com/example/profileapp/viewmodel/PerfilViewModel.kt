package com.example.profileapp.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.profileapp.model.Perfil

class PerfilViewModel : ViewModel() {

    val perfil = mutableStateOf(
        Perfil(
            nombre = "Juan José Gracia Gómez",
            edad = 21,
            ciudad = "Zipaquirá",
            correo = "jjgracia@ucundinamarca.edu.co",
            programa = "Ingeniería de Sistemas y Computación",
            semestre = 5,
            descripcion = "Soy una persona que disfruta mucho el equilibrio entre lo digital y el movimiento. Me gusta la tecnología y la música, pero también me hace falta salir, ya sea en la bici o simplemente a caminar para desconectar. Soy curioso por naturaleza, me gusta entender cómo funcionan las cosas que uso y siempre estoy buscando nuevos retos",


            hobbies = listOf(
                "Videojuegos",
                "Música"
            ),

            pasatiempos = listOf(
                "Ver películas y videos de YouTube",
                "Caminar con mi perro",
                "Compartir con amigos",
                "Navegar en redes sociales"
            ),

            deportes = listOf(
                "Ciclismo de ruta",
                "Ciclismo Cross Country",
                "Ping pong (Tenis de mesa)"
            ),

            intereses = listOf(
                "Componentes de computadoras (Hardware)",
                "Viajes",
                "MMA"
            )
        )
    )

    val mostrarHobbies = mutableStateOf(false)
    val mostrarPasatiempos = mutableStateOf(false)
    val mostrarDeportes = mutableStateOf(false)
    val mostrarIntereses = mutableStateOf(false)

    val modoPrivado = mutableStateOf(false)

    // MODO OSCURO
    private val _modoOscuro = mutableStateOf(false)
    val modoOscuro: State<Boolean> = _modoOscuro

    fun toggleModoOscuro() {
        _modoOscuro.value = !_modoOscuro.value
    }

    fun toggleModoPrivado() {
        modoPrivado.value = !modoPrivado.value
    }

    fun toggleHobbies() {
        mostrarHobbies.value = !mostrarHobbies.value
    }

    fun togglePasatiempos() {
        mostrarPasatiempos.value = !mostrarPasatiempos.value
    }

    fun toggleDeportes() {
        mostrarDeportes.value = !mostrarDeportes.value
    }

    fun toggleIntereses() {
        mostrarIntereses.value = !mostrarIntereses.value
    }
}