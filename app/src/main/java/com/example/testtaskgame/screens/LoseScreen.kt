package com.example.testtaskgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testtaskgame.MainViewModel

@Composable
fun LoseScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "You Lose!",
            color = Color.Red,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(onClick = {
            viewModel.restart()
            navController.navigate("main")
        }) {
            Icon(
                Icons.Filled.Home, contentDescription = "Home"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        IconButton(onClick = {
            navController.clearBackStack("game")
            navController.clearBackStack("lose")
            viewModel.restart()
            navController.popBackStack()
        }) {
            Icon(
                Icons.Filled.Refresh, contentDescription = "Again"
            )
        }
    }
}