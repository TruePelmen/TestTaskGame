package com.example.testtaskgame

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskgame.screens.Meteor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.sqrt

class MainViewModel: ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading
    private val _spaceshipPosition = MutableStateFlow(Offset(0f, 0f))
    val spaceshipPosition: StateFlow<Offset> = _spaceshipPosition

    private val _meteors = MutableStateFlow<List<Meteor>>(emptyList())
    val meteors: StateFlow<List<Meteor>> = _meteors

    fun startLoading() {
        _isLoading.value = true
    }

    fun stopLoading() {
        _isLoading.value = false
    }

    private fun startGame() {
        viewModelScope.launch {
            while (true) {
                updateMeteors()
                checkCollisions()
                delay(200L)
            }
        }
    }

    private fun updateMeteors() {
        _meteors.value = _meteors.value.map { meteor ->
            meteor.copy(position = Offset(meteor.position.x, meteor.position.y + 10))
        }
    }

    private fun checkCollisions() {
        val spaceship = _spaceshipPosition.value
        val collision = _meteors.value.any { meteor ->
            // Simple collision check logic
            val dx = spaceship.x - meteor.position.x
            val dy = spaceship.y - meteor.position.y
            val distance = sqrt(dx * dx + dy * dy)
            distance < 50 // Assumes 50 as collision distance threshold
        }
        if (collision) {
            // Handle collision (e.g., end game)
        }
    }
        
    fun onScreenTapped(position: Offset) {
        _spaceshipPosition.value = position
    }

    init {
        _isLoading.value = true
        viewModelScope.launch {
            delay(3000L)
            stopLoading()
        }
    }

}