package com.example.testtaskgame

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testtaskgame.screens.Meteor
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.sqrt
import kotlin.random.Random

class MainViewModel : ViewModel() {
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

    fun startGame(screenWidth: Float, screenHeight: Float) {
        initializeMeteors(screenWidth)
        viewModelScope.launch {
            while (true) {
                updateMeteors(screenWidth, screenHeight)
                checkCollisions()
                delay(100L)
            }
        }
    }

    private fun generateMeteors(screenWidth: Float) {
        _meteors.value = List(5) {
            Meteor(position = Offset(Random.nextFloat() * screenWidth, 0f))
        }
    }

    private fun initializeMeteors(screenWidth: Float) {
        val initialMeteors = List(5) {
            Meteor(position = Offset(Random.nextFloat() * screenWidth, Random.nextFloat() * -screenWidth))
        }
        _meteors.value = initialMeteors
    }

    private fun updateMeteors(screenWidth: Float, screenHeight: Float) {
        val newMeteors = _meteors.value.mapNotNull { meteor ->
            val newY = meteor.position.y + 8
            if (newY > screenHeight) {
                null
            } else {
                meteor.copy(position = Offset(meteor.position.x, newY))
            }
        }.toMutableList()

        while (newMeteors.size < 5) {
            newMeteors.add(Meteor(position = Offset(Random.nextFloat() * screenWidth, 0f)))
        }

        _meteors.value = newMeteors
    }

    private fun checkCollisions() {
        val spaceship = _spaceshipPosition.value
        val collision = _meteors.value.any { meteor ->
            val dx = spaceship.x - meteor.position.x
            val dy = spaceship.y - meteor.position.y
            val distance = sqrt(dx * dx + dy * dy)
            distance < 50
        }
        if (collision) {
            // Handle collision (e.g., end game)
        }
    }

    fun onScreenTapped(position: Offset, density: Float) {
        val newPosition = Offset(position.x / density, position.y / density)
        _spaceshipPosition.value = newPosition
    }

    init {
        _isLoading.value = true
        viewModelScope.launch {
            delay(3000L)
            stopLoading()
        }
    }
}