package com.example.testtaskgame.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testtaskgame.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun GameScreen(navController: NavController) {
    val spaceshipY = 0f
    val spaceshipX = remember { Animatable(0f) }
    val screenWidth = remember { mutableStateOf(0f) }
    val screenHeight = remember { mutableStateOf(0f) }
    val scope = rememberCoroutineScope()

    // List of meteors
    val meteors = remember { mutableStateListOf<Meteor>() }

    Image(
        painter = painterResource(id = R.drawable.space_sky),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds
    )

    LaunchedEffect(Unit) {
        while (true) {
            meteors.add(Meteor(Offset(Random.nextFloat() * screenWidth.value, 0f)))
            delay(5000L)
        }
    }

    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Transparent)
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    scope.launch {
                        spaceshipX.snapTo(spaceshipX.value + dragAmount.x)
                    }
                }
            }
    ) {
        meteors.forEach { meteor ->
            val meteorAlpha = remember { Animatable(1f) }
            LaunchedEffect(meteor) {
                meteorAlpha.animateTo(
                    targetValue = 0f,
                    animationSpec = tween(
                        durationMillis = 3000,
                        easing = LinearEasing
                    )
                )
                meteors.remove(meteor)
            }
            Image(
                painter = painterResource(id = R.drawable.asteroid),
                contentDescription = "Meteor",
                modifier = Modifier
                    .offset(x = meteor.position.x.dp, y = meteor.position.y.dp)
                    .alpha(meteorAlpha.value)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.spaceship),
            contentDescription = "Spaceship",
            modifier = Modifier
                .offset(x = spaceshipX.value.dp, y = (screenHeight.value - 100).dp)
                .size(100.dp)
        )
    }
}

data class Meteor(val position: Offset)
