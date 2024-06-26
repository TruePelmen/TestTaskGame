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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testtaskgame.MainActivity
import com.example.testtaskgame.MainViewModel


@Composable
fun SettingsScreen(navController: NavController, viewModel: MainViewModel) {
    val isMusicOn by viewModel.isMusicOn.collectAsState()

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
                    IconButton(onClick = { navController.navigate("main") }) {
                        Icon(Icons.Filled.PlayArrow, contentDescription = "MainScreen")
                    }
                    IconButton(onClick = { navController.navigate("shop") }) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Shop")
                    }
                    IconButton(onClick = { navController.navigate("results") }) {
                        Icon(Icons.Filled.List, contentDescription = "Results")
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Settings", modifier = Modifier.fillMaxSize())
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        ) {
            Text(text = "Settings", fontSize = 30.sp, modifier = Modifier.padding(bottom = 16.dp))
            Button(onClick = {
                viewModel.toggleMusic()
                if (isMusicOn) {
                    (navController.context as MainActivity).stopMusicService()
                } else {
                    (navController.context as MainActivity).startMusicService()
                }
            }, modifier = Modifier.padding(bottom = 8.dp)) {
                Text(text = if (isMusicOn) "Turn Music Off" else "Turn Music On")
            }
            Button(onClick = { /*TODO: Implement controls settings*/ }, modifier = Modifier.padding(bottom = 8.dp)) {
                Text(text = "Controls Settings")
            }
            Button(onClick = { /*TODO: Implement graphics settings*/ }, modifier = Modifier.padding(bottom = 8.dp)) {
                Text(text = "Graphics Settings")
            }
        }
    }
}