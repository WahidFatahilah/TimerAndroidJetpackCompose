package com.pomodoro.pomodoroappsmoa.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pomodoro.pomodoroappsmoa.R

@Composable
fun CountDownTopBar(
    title: String,
    onBackClick: () -> Unit,
    onAudioClick: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back button"
                )
            }
        },
        actions = {
            IconButton(onClick = onAudioClick) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Audio button"
                )
            }
        }
    )
}

@Composable
fun NameDropDownList(
    nameList: List<String>,
    selectedName: String,
    onNameSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val icon = painterResource(R.drawable.baseline_arrow_drop_down_24)

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = selectedName,
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .padding(16.dp)
                .background(Color.LightGray)
        )
        IconButton(
            onClick = { expanded = true },
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(icon, contentDescription = "Dropdown Icon")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            nameList.forEach { name ->
                DropdownMenuItem(
                    onClick = {
                        onNameSelected(name)
                        expanded = false
                    }
                ) {
                    Text(
                        text = name,
                        modifier = Modifier.padding(8.dp),
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}