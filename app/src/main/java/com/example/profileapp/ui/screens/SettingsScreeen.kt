package com.example.profileapp.ui.screens

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.profileapp.viewmodel.PerfilViewModel

@Composable
fun SettingsScreen(navController: NavController, viewModel: PerfilViewModel) {

    val activity = navController.context as Activity

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {

        Text(
            text = "Configuración",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(20.dp))

        // MODO OSCURO
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = Icons.Default.DarkMode,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Modo oscuro",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Switch(
                checked = viewModel.modoOscuro.value,
                onCheckedChange = { viewModel.toggleModoOscuro() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // MODO PRIVADO
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Icon(
                    imageVector = if (viewModel.modoPrivado.value)
                        Icons.Default.Lock
                    else
                        Icons.Default.LockOpen,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Modo privado",
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Switch(
                checked = viewModel.modoPrivado.value,
                onCheckedChange = { viewModel.toggleModoPrivado() }
            )
        }

        // 🔥 EMPUJA TODO HACIA ARRIBA
        Spacer(modifier = Modifier.weight(1f))

        // PARTE INFERIOR
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Versión 1.0",
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = { navController.popBackStack() }
            ) {
                Text("Volver")
            }
        }
    }
}