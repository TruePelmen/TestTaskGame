package com.example.testtaskgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testtaskgame.MainViewModel
import com.example.testtaskgame.R

@Composable
fun GameScreen(viewModel: MainViewModel) {
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val screenWidth = with(density) { configuration.screenWidthDp.dp.toPx() }
    val screenHeight = with(density) { configuration.screenHeightDp.dp.toPx() }

    Image(
        painter = painterResource(id = R.drawable.space_sky),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    val spaceshipPosition by viewModel.spaceshipPosition.collectAsState()
    val meteors by viewModel.meteors.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.startGame(screenWidth, screenHeight)
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .pointerInput(Unit) {
                detectTapGestures { position ->
                    viewModel.onScreenTapped(position, density.density)
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
        Image(
            painter = painterResource(id = R.drawable.spaceship),
            contentDescription = "Spaceship",
            modifier = Modifier
                .offset(x = spaceshipPosition.x.dp, y = spaceshipPosition.y.dp)
                .size(100.dp)
        )
    }
}

data class Meteor(val position: Offset)
