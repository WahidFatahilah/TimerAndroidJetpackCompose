package com.pomodoro.pomodoroappsmoa.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun TimerUI(viewModel: TimerUIViewModel) {
    val duration by viewModel.duration.collectAsState()
    val namaKegiatan by viewModel.namaKegiatan.collectAsState()
    val remainingTime by viewModel.remainingTime.collectAsState()
    val isRunning by viewModel.isRunning.collectAsState()
    val isPaused by viewModel.isPaused.collectAsState()
    val showTimerDialog by viewModel.showTimerDialog.collectAsState()
/*    val nameList = viewModel.nameList*/
    val selectedName by viewModel.selectedName.collectAsState()




}