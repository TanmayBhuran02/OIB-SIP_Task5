package com.example.stopwatch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LapAdapter(private var lapList: List<Lap> = mutableListOf()) : RecyclerView.Adapter<LapAdapter.LapViewHolder>() {

    fun setLapList(list: List<Lap>) {
        lapList = list.toMutableList() // Convert to mutable list and assign
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LapViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_lap, parent, false)
        return LapViewHolder(view)
    }

    override fun onBindViewHolder(holder: LapViewHolder, position: Int) {
        val lap = lapList[position]
        holder.lapNumberTextView.text = "Lap ${lap.lapNumber}"
        holder.lapTimeTextView.text = lap.lapTime
    }

    override fun getItemCount(): Int {
        return lapList.size
    }

    class LapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val lapNumberTextView: TextView = itemView.findViewById(R.id.lapNumberTextView)
        val lapTimeTextView: TextView = itemView.findViewById(R.id.lapTimeTextView)
    }
}