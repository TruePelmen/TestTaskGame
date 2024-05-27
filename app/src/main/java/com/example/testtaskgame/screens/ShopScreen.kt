package com.example.testtaskgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testtaskgame.MainViewModel
import com.example.testtaskgame.R

data class ShopItem(val name: String, val price: Int)

val shopItems = listOf(
    ShopItem("Default Spaceship", 0),
    ShopItem("Spaceship 1", 10),
    ShopItem("Spaceship 2", 50),
    ShopItem("Spaceship 3", 75)
)

@Composable
fun ShopScreen(navController: NavController, viewModel: MainViewModel) {
    val totalCoins by viewModel.totalCoins.collectAsState()
    val ownedSpaceships by viewModel.ownedSpaceships.collectAsState()
    val selectedSpaceship by viewModel.selectedSpaceship.collectAsState()

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
                Text(text = "Total Coins: $totalCoins", fontSize = 20.sp, modifier = Modifier.padding(bottom = 16.dp))
            }
            items(shopItems) { item ->
                val spaceshipImageRes = when (item.name) {
                    "Spaceship 1" -> R.drawable.spaceship_2
                    "Spaceship 2" -> R.drawable.spaceship_3
                    "Spaceship 3" -> R.drawable.spaceship_4
                    else -> R.drawable.spaceship
                }

                Row(
                    modifier = Modifier.padding(bottom = 16.dp).fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = item.name, fontSize = 20.sp)
                        Text(
                            text = "Price: ${item.price} coins",
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        if (item.name in ownedSpaceships) {
                            Button(onClick = { viewModel.selectSpaceship(item.name) }) {
                                Text(text = if (item.name == selectedSpaceship) "Selected" else "Select")
                            }
                        } else {
                            Button(onClick = { viewModel.buySpaceship(item.name, item.price) }) {
                                Text(text = "Buy")
                            }
                        }
                    }
                    Image(
                        painter = painterResource(id = spaceshipImageRes),
                        contentDescription = item.name,
                        modifier = Modifier.size(64.dp)
                    )
                }
            }
        }
    }
}
