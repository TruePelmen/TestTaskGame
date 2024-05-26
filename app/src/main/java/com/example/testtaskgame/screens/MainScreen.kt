package com.example.testtaskgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testtaskgame.R

@Composable
fun MainScreen(navController: NavController) {
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
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.PlayArrow, contentDescription = "MainScreen", modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(onClick = { navController.navigate("shop") }) {
                        Icon(
                            Icons.Filled.ShoppingCart, contentDescription = "Shop"
                        )
                    }
                    IconButton(onClick = { navController.navigate("results")}) {
                        Icon(
                            Icons.Filled.List, contentDescription = "Results"
                        )
                    }
                    IconButton(onClick = { navController.navigate("settings") }) {
                        Icon(
                            Icons.Filled.Settings, contentDescription = "Settings"
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Image(
            painter = painterResource(id = R.drawable.space_sky),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(innerPadding)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = "Welcome to Space Odyssey",
                    Modifier.padding(20.dp),
                    color = Color.Magenta,
                    fontSize = 25.sp,
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = { navController.navigate("game") },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(text = "Play", fontSize = 20.sp)
                }
            }
        }
    }
}
