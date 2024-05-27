package com.example.testtaskgame

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.testtaskgame.navigation.AppNavigation
import com.example.testtaskgame.services.MusicService

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
        startMusicService()

        enableEdgeToEdge()
        setContent {
            AppNavigation(viewModel = viewModel)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopMusicService()
    }

    fun startMusicService() {
        val intent = Intent(this, MusicService::class.java)
        startService(intent)
    }

    fun stopMusicService() {
        val intent = Intent(this, MusicService::class.java)
        stopService(intent)
    }
}


