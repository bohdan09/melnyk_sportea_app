package com.example.melnyk_sportea_app.utils

import android.os.CountDownTimer
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView

class Timer {
    private lateinit var timer: CountDownTimer
    private val timeFormatter = TimeFormatter()
    private var startStopTime = 0L
    var isStopped = true

    fun startTimer(
        time: Long,
        timerText: TextView?,
        setFlag: (Boolean) -> Unit,
        progressBar: ProgressBar
    ) {
        if (startStopTime == 0L) {
            startStopTime = time
        }
        timer = object : CountDownTimer(startStopTime, 1000) {
            override fun onTick(tick: Long) {
                startStopTime = tick
                progressBar.progress = progressBar.progress - (100 / (time / 1000 % 60)).toInt()
                updateTimer(timerText = timerText)
            }

            override fun onFinish() {
                isStopped = true
                setFlag(isStopped)
            }

        }.start()
        isStopped = false
    }

    private fun updateTimer(timerText: TextView?) {
        timerText?.text = timeFormatter.getSecondTime(time = startStopTime)
    }

    fun pauseTimer() {
        isStopped = true
        timer.cancel()
    }
}