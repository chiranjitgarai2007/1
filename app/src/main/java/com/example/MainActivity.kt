package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.data.AppDatabase
import com.example.data.SettingsRepository
import com.example.ui.MainViewModel
import com.example.ui.MainViewModelFactory
import com.example.ui.navigation.MathNavGraph
import com.example.ui.theme.AppTheme
import com.example.ui.theme.DarkGrayBackground

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = AppDatabase.getDatabase(this)
        val settingsRepository = SettingsRepository(this)
        val factory = MainViewModelFactory(db, settingsRepository)
        viewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]

        setContent {
            AppTheme(darkTheme = true) { // Enforcing dark theme for premium feel
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = DarkGrayBackground
                ) {
                    val navController = rememberNavController()
                    MathNavGraph(navController = navController, viewModel = viewModel)
                }
            }
        }
    }
}

