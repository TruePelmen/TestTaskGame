package com.example.testtaskgame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testtaskgame.R

@Composable
fun MainScreen(modifier: Modifier)
{
    Image(painter = painterResource(
        id = R.drawable.space_sky),
        contentDescription = "Background",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.FillBounds)
    Box(contentAlignment = Alignment.Center){
            Text(text = "Auuuuuuuuuuu", Modifier.padding(20.dp), color = Color.Green)
    }
}