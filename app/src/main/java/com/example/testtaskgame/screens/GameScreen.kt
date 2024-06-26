package com.example.testtaskgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testtaskgame.MainViewModel
import com.example.testtaskgame.R

@Composable
fun GameScreen(viewModel: MainViewModel, navController: NavController) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidth = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }

    val spaceshipPosition by viewModel.spaceshipPosition.collectAsState()
    val meteors by viewModel.meteors.collectAsState()
    val isGameOver by viewModel.isGameOver.collectAsState()
    val coins by viewModel.coins.collectAsState()
    val collectedCoins by viewModel.collectedCoins.collectAsState()
    val selectedSpaceship by viewModel.selectedSpaceship.collectAsState()

    val spaceshipImageRes = when (selectedSpaceship) {
        "Spaceship 1" -> R.drawable.spaceship_2
        "Spaceship 2" -> R.drawable.spaceship_3
        "Spaceship 3" -> R.drawable.spaceship_4
        else -> R.drawable.spaceship
    }

    LaunchedEffect(isGameOver) {
        if (isGameOver) {
            viewModel.addCollectedCoins(viewModel.collectedCoins.value)
            navController.navigate("lose")
        }
    }

    LaunchedEffect(Unit) {
        viewModel.startGame(screenWidth, screenHeight)
    }

    Image(
        painter = painterResource(id = R.drawable.space_sky),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    val delta = Offset(dragAmount.x, dragAmount.y)
                    viewModel.moveSpaceship(delta, screenWidth, screenHeight)
                }
            }
    ) {
        meteors.forEach { meteor ->
            Image(
                painter = painterResource(id = R.drawable.asteroid),
                contentDescription = "Asteroid",
                modifier = Modifier
                    .offset(x = meteor.position.x.dp, y = meteor.position.y.dp)
                    .size(50.dp)
            )
        }

        coins.forEach { coin ->
            Image(
                painter = painterResource(id = R.drawable.coin_image),
                contentDescription = "Coin",
                modifier = Modifier
                    .offset(x = coin.position.x.dp, y = coin.position.y.dp)
                    .size(30.dp)
            )
        }

        Image(
            painter = painterResource(id = spaceshipImageRes),
            contentDescription = "Spaceship",
            modifier = Modifier
                .size(70.dp)
                .offset(x = spaceshipPosition.x.dp, y = spaceshipPosition.y.dp)
        )

        Text(
            text = "Coins: $collectedCoins",
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        )
    }
}