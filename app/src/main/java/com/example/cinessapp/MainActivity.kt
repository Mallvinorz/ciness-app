package com.example.cinessapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.cinessapp.core.navigation.AppNavHost
import com.example.cinessapp.ui.presentation.MainScreen
import com.example.cinessapp.ui.theme.CinessAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CinessAppTheme {
                MainScreen()
            }
        }
    }
}