package com.example.testtaskgame

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.testtaskgame.navigation.AppNavigation
import com.example.testtaskgame.screens.LoadingScreen
import com.example.testtaskgame.screens.MainScreen
import com.example.testtaskgame.ui.theme.TestTaskGameTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<MainViewModel>()
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
            setOnExitAnimationListener { screen ->
                val pulseX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    1.0f,
                    1.5f,
                    1.0f
                )
                pulseX.interpolator = OvershootInterpolator()
                pulseX.duration = 1000L

                val pulseY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    1.0f,
                    1.5f,
                    1.0f
                )
                pulseY.interpolator = OvershootInterpolator()
                pulseY.duration = 1000L

                val animatorSet = AnimatorSet()
                animatorSet.playTogether(pulseX, pulseY)
                animatorSet.doOnEnd { screen.remove() }

                animatorSet.start()
            }
        }

        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}


