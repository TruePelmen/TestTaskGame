package com.example.testtaskgame

import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.sqrt
import kotlin.random.Random

class MainViewModel : ViewModel() {

    private val _spaceshipPosition = MutableStateFlow(Offset(0f, 0f))
    val spaceshipPosition: StateFlow<Offset> = _spaceshipPosition

    private val _meteors = MutableStateFlow<List<Meteor>>(emptyList())
    val meteors: StateFlow<List<Meteor>> = _meteors

    private val _isGameOver = MutableStateFlow(false)
    val isGameOver: StateFlow<Boolean> get() = _isGameOver

    private var gameJob = MutableStateFlow<Job?>(null)

    private val _isMusicOn  =  MutableStateFlow(true)
    val isMusicOn: StateFlow<Boolean> =_isMusicOn

    private val _coins = MutableStateFlow<List<Coin>>(emptyList())
    val coins: StateFlow<List<Coin>> = _coins

    private fun initializeCoins(screenWidth: Float) {
        val initialCoins = List(1) {
            Coin(position = Offset(Random.nextFloat() * screenWidth, Random.nextFloat() * -screenWidth))
        }
        _coins.value = initialCoins
    }

    fun toggleMusic() {
        _isMusicOn.value = !_isMusicOn.value
    }

    fun startGame(screenWidth: Float, screenHeight: Float) {
        initializeMeteors(screenWidth)
        gameJob.value?.cancel()
        gameJob.value = viewModelScope.launch {
            while (true) {
                updateMeteors(screenWidth, screenHeight)
                updateCoins(screenWidth, screenHeight)
                checkCollisions()
                checkCoinCollisions() // Додайте виклик методу перевірки зіткнень з монетками
                delay(100L)
            }
        }
    }

    private fun initializeMeteors(screenWidth: Float) {
        val initialMeteors = List(3) {
            Meteor(
                position = Offset(Random.nextFloat() * screenWidth, Random.nextFloat() * -screenWidth),
                speed = Random.nextFloat() * 4 + 5 // Random speed between 5 and 10
            )
        }
        _meteors.value = initialMeteors
    }

    private fun updateMeteors(screenWidth: Float, screenHeight: Float) {
        val newMeteors = _meteors.value.mapNotNull { meteor ->
            val newY = meteor.position.y + meteor.speed
            if (newY > screenHeight) {
                null
            } else {
                meteor.copy(position = Offset(meteor.position.x, newY))
            }
        }.toMutableList()

        if (Random.nextFloat() < 0.2) { // 20% chance to add a new meteor
            newMeteors.add(Meteor(position = Offset(Random.nextFloat() * screenWidth, 0f), speed = Random.nextFloat() * 4 + 5))
        }

        _meteors.value = newMeteors
    }

    private fun updateCoins(screenWidth: Float, screenHeight: Float) {
        val newCoins = _coins.value.mapNotNull { coin ->
            val newY = coin.position.y + 5 // Змініть швидкість руху монетки за вашими потребами
            if (newY > screenHeight) {
                null
            } else {
                coin.copy(position = Offset(coin.position.x, newY))
            }
        }.toMutableList()

        if (Random.nextFloat() < 0.1) { // Змініть шанс з'явлення нової монетки за вашими потребами
            newCoins.add(Coin(position = Offset(Random.nextFloat() * screenWidth, 0f)))
        }

        _coins.value = newCoins
    }


    private fun checkCollisions() {
        val spaceship = _spaceshipPosition.value
        val collision = _meteors.value.any { meteor ->
            val dx = spaceship.x - meteor.position.x
            val dy = spaceship.y - meteor.position.y
            val distance = sqrt(dx * dx + dy * dy)
            distance < 50 // Встановіть відповідне значення порогу визначення зіткнень
        }
        if (collision) {
            _isGameOver.value = true
        }
    }

    private fun checkCoinCollisions() {
        val spaceship = _spaceshipPosition.value
        val collectedCoinIndex = _coins.value.indexOfFirst { coin ->
            val dx = spaceship.x - coin.position.x
            val dy = spaceship.y - coin.position.y
            val distance = sqrt(dx * dx + dy * dy)
            distance < 40 // Встановіть відповідне значення порогу зіткнень з монеткою
        }
        if (collectedCoinIndex != -1) {
            // Видалення зібраної монетки та додавання рахунку гравця
            _coins.value = _coins.value.toMutableList().apply { removeAt(collectedCoinIndex) }
            // Додайте код для збільшення рахунку гравця
        }
    }


    fun moveSpaceship(panDelta: Offset, screenWidth: Float, screenHeight: Float) {
        var newPositionX = _spaceshipPosition.value.x + panDelta.x
        var newPositionY = _spaceshipPosition.value.y + panDelta.y
        if(newPositionX < 0) newPositionX = 0F
        else if(newPositionX > screenWidth)  newPositionX = screenWidth
        if(newPositionY < 0) newPositionY = 0F
        else if(newPositionY > screenHeight)  newPositionY = screenHeight
        _spaceshipPosition.value = Offset(newPositionX, newPositionY)
    }

    fun restart(screenWidth: Float, screenHeight: Float) {
        _isGameOver.value = false
        _spaceshipPosition.value = Offset(100f, 100f)
        initializeMeteors(screenWidth)
        startGame(screenWidth, screenHeight)
    }

    fun reset() {
        _isGameOver.value = false
    }

    init {
        viewModelScope.launch {
            delay(3000L)
        }
    }
}

data class Meteor(val position: Offset, val speed: Float)

data class Coin(val position: Offset)

