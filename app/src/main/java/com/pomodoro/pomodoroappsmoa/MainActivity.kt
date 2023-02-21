package com.pomodoro.pomodoroappsmoa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap.Companion.Round
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pomodoro.pomodoroappsmoa.ui.theme.PomodoroAppsMOATheme
import kotlinx.coroutines.delay
import android.graphics.Canvas
import androidx.compose.foundation.Image

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.res.painterResource


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PomodoroAppsMOATheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    CountDownTimer()
                }
            }
        }
    }
}

@Composable
fun CountDownTimer() {
    var duration by remember { mutableStateOf(10L) }
    var namaKegiatan by remember { mutableStateOf("") }
    var remainingTime by remember { mutableStateOf(10L) }
    var isRunning by remember { mutableStateOf(false) }
    var showTimerDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (isRunning) {
            CircularProgressBar(
                progress = (duration - remainingTime / 1000f) / duration,
                modifier = Modifier.size(400.dp),
                strokeWidth = 35.dp,
            )
            Text(
                text = formatTime(remainingTime),
                fontSize = 50.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = { isRunning = !isRunning },
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "STOP")
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.splashscreen),
                    contentDescription = "gambar splash screen",
                )
                OutlinedTextField(
                    value = duration.toString(),
                    onValueChange = { input ->
                        duration = if (input.isEmpty()) 0 else input.toLong()
                        remainingTime = duration * 1000
                    },
                    label = { Text("Duration in seconds") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
              /*  OutlinedTextField(
                    value = namaKegiatan,
                    onValueChange = { input ->
                        namaKegiatan = if (input.isEmpty()) 0 else input.toLong()
                    },
                    label = { Text("Masukkan nama kegiatan") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )*/

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        isRunning = true
                        remainingTime = duration * 1000
                    },
                   /* modifier = Modifier.align(Alignment.BottomCenter)*/
                ) {
                    Text(text = "START")
                }
            }


        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(10)
            if (isRunning && remainingTime > 0) {
                remainingTime -= 10
            } else if (remainingTime == 0L) {
                isRunning = false
            }
        }
    }
}

private fun formatTime(time: Long): String {
    val milliseconds = time % 1000 / 10
    val seconds = time / 1000 % 60
    val minutes = time / 1000 / 60
/*    return "%02d:%02d:%02d".format(minutes, seconds, milliseconds)*/
    return "%02d:%02d".format(minutes, seconds)
}

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    progress: Float,
    strokeWidth: Dp = 15.dp,
    color: Color = colors.primary,
    backgroundColor: Color = color.copy(alpha = 0.1f)
) {


    val stroke = with(LocalDensity.current) { strokeWidth.toPx() }
    Canvas(modifier = modifier) {
        val radius = (size.minDimension - stroke) / 2
        val startAngle = 270f
        val sweepAngle = 360 - (progress * 360f)
        drawArc(
            color = backgroundColor,
            startAngle = startAngle,
            sweepAngle = 360f,
            useCenter = false,
            style = Stroke(stroke, cap = StrokeCap.Round)
        )
        drawArc(
            color = color,
            startAngle = startAngle,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = Stroke(stroke, cap = StrokeCap.Round)
        )
        if (progress == 0f) {
            drawCircle(
                color = backgroundColor,
                radius = radius - stroke / 2,
                center = Offset(size.width / 2, size.height / 2),
                style = Stroke(stroke, cap = StrokeCap.Round)
            )
            drawCircle(
                color = color,
                radius = radius - stroke / 2,
                center = Offset(size.width / 2, size.height / 2),
                style = Stroke(stroke)
            )
        }
    }
}
private fun DrawScope.drawCircle(
    color: Color,
    radius: Float,
    center: Offset,
    style: Stroke
) {
    drawCircle(
        color = color,
        radius = radius,
        center = center,
        style = style
    )
}

@Composable
fun TimerDialog(onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Waktu habis!") },
        text = { Text(text = "Waktu telah habis.") },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}
