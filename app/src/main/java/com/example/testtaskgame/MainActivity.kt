package com.example.testtaskgame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.example.testtaskgame.navigation.AppNavigation

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

        enableEdgeToEdge()
        setContent {
            AppNavigation(viewModel = viewModel)
        }
    }
}


