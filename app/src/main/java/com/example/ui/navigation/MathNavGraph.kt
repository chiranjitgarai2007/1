package com.example.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.ui.MainViewModel
import com.example.ui.screens.*

@Composable
fun MathNavGraph(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable("chapters") {
            ChaptersScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            "chapter_detail/{chapterId}",
            arguments = listOf(navArgument("chapterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val chapterId = backStackEntry.arguments?.getInt("chapterId") ?: 1
            ChapterDetailScreen(navController = navController, viewModel = viewModel, chapterId = chapterId)
        }
        composable(
            "level/{levelId}",
            arguments = listOf(navArgument("levelId") { type = NavType.IntType })
        ) { backStackEntry ->
            val levelId = backStackEntry.arguments?.getInt("levelId") ?: 1
            LevelScreen(navController = navController, viewModel = viewModel, levelId = levelId)
        }
        composable("settings") {
            SettingsScreen(navController = navController, viewModel = viewModel)
        }
    }
}
