package dev.vengateshm.android_kotlin_compose_practice.audio_player_view

import android.media.MediaPlayer
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AudioPlayerViewModel : ViewModel() {

    private val tag = AudioPlayerViewModel::class.java.simpleName

    private var mediaTimer: CountDownTimer? = null

    private val _currentMediaTimeInMillis = MutableLiveData(0)
    val currentMediaTimeInMillis: LiveData<Int> = _currentMediaTimeInMillis

    private val _isAudioCompleted = MutableLiveData(false)
    val isAudioCompleted: LiveData<Boolean> = _isAudioCompleted

    fun initMediaTimer(mediaPlayer: MediaPlayer) {
        mediaTimer = object : CountDownTimer(mediaPlayer.duration.toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                _currentMediaTimeInMillis.value = mediaPlayer.currentPosition
            }

            override fun onFinish() {
                _isAudioCompleted.value = true
                Log.d(tag, "onFinish : Media player finished")
            }
        }
        mediaTimer?.start()
    }

    override fun onCleared() {
        super.onCleared()
        mediaTimer?.cancel()
        mediaTimer = null
    }
}