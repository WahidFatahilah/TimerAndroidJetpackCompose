package com.pomodoro.pomodoroappsmoa.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TimerUIViewModel : ViewModel() {
    private val _duration = MutableStateFlow(10L)
    val duration: StateFlow<Long> = _duration.asStateFlow()

    private val _namaKegiatan = MutableStateFlow("")
    val namaKegiatan: StateFlow<String> = _namaKegiatan.asStateFlow()

    private val _remainingTime = MutableStateFlow(10L)
    val remainingTime: StateFlow<Long> = _remainingTime.asStateFlow()

    private val _isRunning = MutableStateFlow(false)
    val isRunning: StateFlow<Boolean> = _isRunning.asStateFlow()

    private val _isPaused = MutableStateFlow(false)
    val isPaused: StateFlow<Boolean> = _isPaused.asStateFlow()

    private val _showTimerDialog = MutableStateFlow(false)
    val showTimerDialog: StateFlow<Boolean> = _showTimerDialog.asStateFlow()

    private val _nameList = MutableStateFlow(listOf("Mengerjakan Pr", "Membaca Buku", "Mendengar Podcast"))
    val nameList: StateFlow<List<String>> = _nameList
    private val _selectedName = MutableStateFlow("")
    val selectedName: StateFlow<String> = _selectedName

    fun setDuration(duration: Long) {
        _duration.value = duration
        _remainingTime.value = duration * 1000
    }

    fun setSelectedName(name: String) {
        _selectedName.value = name
    }

    fun startTimer() {
        _isRunning.value = true
    }

    fun pauseTimer() {
        _isPaused.value = true
    }

    fun resumeTimer() {
        _isPaused.value = false
    }

    fun stopTimer() {
        _isRunning.value = false
    }

    fun tickTimer() {
        if (isRunning.value && remainingTime.value > 0 && !isPaused.value) {
            _remainingTime.value -= 10
        } else if (remainingTime.value == 0L) {
            _isRunning.value = false


        }


    }




}