package com.example.testtaskgame

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.testtaskgame.navigation.AppNavigation
import com.example.testtaskgame.services.MusicService

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel by viewModels<MainViewModel>()
        super.onCreate(savedInstanceState)
//        installSplashScreen().apply {
//            setKeepOnScreenCondition {
//                viewModel.isLoading.value
//            }
//            setOnExitAnimationListener { screen ->
//                val pulseX = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_X,
//                    1.0f,
//                    1.5f,
//                    1.0f
//                )
//                pulseX.interpolator = OvershootInterpolator()
//                pulseX.duration = 1000L
//
//                val pulseY = ObjectAnimator.ofFloat(
//                    screen.iconView,
//                    View.SCALE_Y,
//                    1.0f,
//                    1.5f,
//                    1.0f
//                )
//                pulseY.interpolator = OvershootInterpolator()
//                pulseY.duration = 1000L
//
//                val animatorSet = AnimatorSet()
//                animatorSet.playTogether(pulseX, pulseY)
//                animatorSet.doOnEnd { screen.remove() }
//
//                animatorSet.start()
//            }
//        }
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

    private fun toggleMusicService(context: Context, isMusicOn: Boolean) {
        val intent = Intent(context, MusicService::class.java)
        if (isMusicOn) {
            startService(intent)
        } else {
            stopService(intent)
        }
    }

    private fun pauseMusicService() {
        val intent = Intent(this, MusicService::class.java)
        intent.action = "PAUSE_MUSIC"
        startService(intent)
    }

    private fun resumeMusicService() {
        val intent = Intent(this, MusicService::class.java)
        intent.action = "RESUME_MUSIC"
        startService(intent)
    }
}


