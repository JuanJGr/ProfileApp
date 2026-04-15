package com.example.profileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.profileapp.ui.screens.MainScreen
import com.example.profileapp.ui.screens.SettingsScreen
import com.example.profileapp.viewmodel.PerfilViewModel
import com.example.profileapp.ui.theme.ProfileAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val viewModel: PerfilViewModel = viewModel()
            val navController = rememberNavController()


            val darkTheme by viewModel.modoOscuro

            ProfileAppTheme(
                darkTheme = darkTheme
            ) {

                NavHost(
                    navController = navController,
                    startDestination = "main"
                ) {

                    composable("main") {
                        MainScreen(navController, viewModel)
                    }

                    composable("settings") {
                        SettingsScreen(navController, viewModel)
                    }
                }
            }
        }
    }
}