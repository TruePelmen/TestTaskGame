package com.example.testtaskgame.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testtaskgame.MainViewModel
import com.example.testtaskgame.screens.GameScreen
import com.example.testtaskgame.screens.LoseScreen
import com.example.testtaskgame.screens.MainScreen
import com.example.testtaskgame.screens.ResultsScreen
import com.example.testtaskgame.screens.SettingsScreen
import com.example.testtaskgame.screens.ShopScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier, viewModel: MainViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "main"
    ) {
        composable("main") { MainScreen(navController, viewModel) }
        composable("game") { GameScreen(viewModel, navController) }
        composable("settings") { SettingsScreen(navController, viewModel) }
        composable("results") { ResultsScreen(navController) }
        composable("shop") { ShopScreen(navController)}
        composable("lose") { LoseScreen(navController, viewModel) }
    }
}

