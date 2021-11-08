package com.example.melnyk_sportea_app.utils

import android.os.CountDownTimer
import android.widget.ProgressBar
import android.widget.TextView

class Timer {
    private lateinit var timer: CountDownTimer
    private val timeFormatter = TimeFormatter()
    var startStopTime = 0L
        private set
    var isStopped = true
        private set

    fun startTimer(
        time: Long,
        timerText: TextView?,
        setFlag: (Boolean) -> Unit,
        progressBar: ProgressBar
    ) {
        if (startStopTime < 1000L) {
            startStopTime = time
        }

        timer = object : CountDownTimer(startStopTime, 1000) {
            override fun onTick(tick: Long) {
                startStopTime = tick
                if (tick < 1000) {
                    progressBar.progress = 0
                } else progressBar.progress = ((tick * 100) / time).toInt()
                updateTimer(timerText = timerText)
                //progressBar.progress - (100 / ((time / 1000) % 60)).toInt()

            }

            override fun onFinish() {
                isStopped = true
                progressBar.progress = 100
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

    fun resetTimer() {
        startStopTime = 0L
    }
}