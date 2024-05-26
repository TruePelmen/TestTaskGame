package com.example.testtaskgame.services

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.example.testtaskgame.R

class MusicService : Service() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate() {
        super.onCreate()
        // Ініціалізуємо MediaPlayer
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        mediaPlayer.isLooping = true // Зациклюємо музику
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Запускаємо музику
        mediaPlayer.start()
        return START_STICKY
    }

    override fun onDestroy() {
        // Зупиняємо та звільняємо ресурси
        mediaPlayer.stop()
        mediaPlayer.release()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}