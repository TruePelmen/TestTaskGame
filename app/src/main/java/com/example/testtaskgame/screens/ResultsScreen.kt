package com.example.testtaskgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ResultsScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate("main")}) {
                        Icon(
                            Icons.Filled.PlayArrow, contentDescription = "MainScreen"
                        )
                    }
                    IconButton(onClick = { navController.navigate("shop") }) {
                        Icon(
                            Icons.Filled.ShoppingCart, contentDescription = "Shop"
                        )
                    }
                    IconButton(onClick = {}) {
                        Icon(
                            Icons.Filled.List, contentDescription = "Results", modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(onClick = {  navController.navigate("settings")}) {
                        Icon(
                            Icons.Filled.Settings, contentDescription = "Settings"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        ) {
            Text(text = "Results", fontSize = 30.sp, modifier = Modifier.padding(bottom = 16.dp))
            // Sample results list
            Text(text = "1. Player1 - 1000 points", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "2. Player2 - 900 points", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "3. Player3 - 800 points", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))
        }
    }
}