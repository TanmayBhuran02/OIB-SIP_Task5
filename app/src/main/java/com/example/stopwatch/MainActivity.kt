package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var chronometer: Chronometer
    private lateinit var startButton: Button
    private lateinit var stopButton: Button
    private lateinit var resetButton: Button
    private lateinit var lapButton: Button
    private lateinit var lapRecyclerView: RecyclerView

    private var isRunning = false
    private var lapCounter = 1
    private var lapList = mutableListOf<Lap>()
    private lateinit var lapAdapter: LapAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chronometer = findViewById(R.id.chronometer)
        startButton = findViewById(R.id.startButton)
        stopButton = findViewById(R.id.stopButton)
        resetButton = findViewById(R.id.resetButton)
        lapButton = findViewById(R.id.lapButton)
        lapRecyclerView = findViewById(R.id.lapRecyclerView)

        // Initialize RecyclerView and its adapter
        lapAdapter = LapAdapter()
        lapRecyclerView.layoutManager = LinearLayoutManager(this)
        lapRecyclerView.adapter = lapAdapter
    }

    fun startTimer(view: View) {
        if (!isRunning) {
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
            isRunning = true
        }
    }

    fun stopTimer(view: View) {
        if (isRunning) {
            chronometer.stop()
            isRunning = false
        }
    }

    fun resetTimer(view: View) {
        chronometer.base = SystemClock.elapsedRealtime()
        if (isRunning) {
            chronometer.stop()
            isRunning = false
        }
        lapList.clear()
        lapCounter = 1
        lapAdapter.setLapList(lapList) // Update the lap list in the adapter
    }


    fun recordLap(view: View) {
        if (isRunning) {
            val elapsedTime = SystemClock.elapsedRealtime() - chronometer.base
            val formattedTime = formatElapsedTime(elapsedTime)
            lapList.add(0, Lap(lapCounter++, formattedTime))
            lapAdapter.setLapList(lapList)
        }
    }

    private fun formatElapsedTime(elapsedTime: Long): String {
        val minutes = (elapsedTime / 60000).toInt()
        val seconds = ((elapsedTime % 60000) / 1000).toInt()
        val millis = (elapsedTime % 1000).toInt()
        return String.format(Locale.getDefault(),"%02d:%02d:%02d", minutes, seconds, millis)
    }
}