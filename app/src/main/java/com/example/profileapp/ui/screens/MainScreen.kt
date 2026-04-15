package com.example.profileapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import coil.compose.AsyncImage
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.example.profileapp.R
import com.example.profileapp.viewmodel.PerfilViewModel
import com.example.profileapp.ui.components.ExpandableSection

@Composable
fun MainScreen(navController: NavController, viewModel: PerfilViewModel) {

    val perfil = viewModel.perfil.value

    var showDescripcion by remember { mutableStateOf(false) }
    var showInfo by remember { mutableStateOf(false) }

    val blurActive = showDescripcion || showInfo

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = if (viewModel.modoOscuro.value) {
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFF0A192F),
                            Color(0xFF112240),
                            Color(0xFF1B263B)
                        )
                    )
                } else {
                    Brush.verticalGradient(
                        listOf(
                            Color(0xFFE3F2FD),
                            Color(0xFF90CAF9)
                        )
                    )
                }
            )
            .blur(if (blurActive) 8.dp else 0.dp)
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = { navController.navigate("settings") }) {
                Icon(Icons.Default.Settings, contentDescription = null)
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = R.drawable.perfil,
                contentDescription = null,
                error = painterResource(id = R.drawable.no_image),
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(60.dp))
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                perfil.nombre,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                perfil.programa,
                color = MaterialTheme.colorScheme.onBackground
            )

            Text(
                "Semestre ${perfil.semestre}",
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // DESCRIPCIÓN
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDescripcion = true },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Descripción personal",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(Icons.Default.Add, null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // INFO
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showInfo = true },
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "Información adicional",
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Icon(Icons.Default.Add, null)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        ExpandableSection("Hobbies", viewModel.mostrarHobbies.value, { viewModel.toggleHobbies() }, perfil.hobbies)
        ExpandableSection("Pasatiempos", viewModel.mostrarPasatiempos.value, { viewModel.togglePasatiempos() }, perfil.pasatiempos)
        ExpandableSection("Deportes", viewModel.mostrarDeportes.value, { viewModel.toggleDeportes() }, perfil.deportes)
        ExpandableSection("Intereses", viewModel.mostrarIntereses.value, { viewModel.toggleIntereses() }, perfil.intereses)
    }

    // MODAL DESCRIPCIÓN
    if (showDescripcion) {
        AlertDialog(
            onDismissRequest = { showDescripcion = false },
            confirmButton = {},
            text = {
                Box {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp, end = 24.dp)
                    ) {
                        Text(
                            "Descripción personal",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            perfil.descripcion,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = { showDescripcion = false },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        )
    }

    // MODAL INFO (ARREGLADO)
    if (showInfo) {
        AlertDialog(
            onDismissRequest = { showInfo = false },
            confirmButton = {},
            text = {
                Box {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp, end = 24.dp)
                    ) {
                        Text(
                            "Información adicional",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text("Edad: ${perfil.edad}", color = MaterialTheme.colorScheme.onSurface)
                        Text("Ciudad: ${perfil.ciudad}", color = MaterialTheme.colorScheme.onSurface)
                        Text(
                            "Correo: " + if (viewModel.modoPrivado.value) "*****" else perfil.correo,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    IconButton(
                        onClick = { showInfo = false },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(4.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = null)
                    }
                }
            }
        )
    }
}