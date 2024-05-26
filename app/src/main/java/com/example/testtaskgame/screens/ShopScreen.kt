package com.example.testtaskgame.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

data class ShopItem(val name: String, val price: Int)

val shopItems = listOf(
    ShopItem("New Spaceship", 100),
    ShopItem("New Background", 50),
    ShopItem("Captain Costume", 75)
)

@Composable
fun ShopScreen(navController: NavController) {
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
                    IconButton(onClick = {  }) {
                        Icon(
                            Icons.Filled.ShoppingCart,
                            contentDescription = "Shop",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    IconButton(onClick = {navController.navigate("results")}) {
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
        LazyColumn(
            modifier = Modifier.padding(innerPadding).padding(16.dp)
        ) {
            item {
                Text(text = "Shop", fontSize = 30.sp, modifier = Modifier.padding(bottom = 16.dp))
            }
            items(shopItems) { item ->
                Column(modifier = Modifier.padding(bottom = 16.dp)) {
                    Text(text = item.name, fontSize = 20.sp)
                    Text(
                        text = "Price: ${item.price} coins",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(bottom = 8.dp))
                    Button(onClick = { /*TODO: Implement purchase functionality*/ }) {
                        Text(text = "Buy")
                    }
                }
            }
        }
    }
}