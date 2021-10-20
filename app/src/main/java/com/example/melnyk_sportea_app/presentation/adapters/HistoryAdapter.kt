package com.example.melnyk_sportea_app.presentation.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.HistoryItemBinding
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.utils.TimeFormatter

class HistoryAdapter : RecyclerView.Adapter<HistoryAdapter.Holder>() {
    private var trainingJournal = listOf<TrainingJournal>()
    private val timeFormatter = TimeFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(trainingJournal[position])
    }

    override fun getItemCount(): Int = trainingJournal.size

    fun setTrainingJournal(journal: List<TrainingJournal>) {
        trainingJournal = journal
        notifyDataSetChanged()
    }

    inner class Holder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = HistoryItemBinding.bind(item)
        fun bind(training: TrainingJournal) {
            with(binding){
                historyDate.text = timeFormatter.getDate(training.date)
                programName.text = training.programName
                durationHistory.text = timeFormatter.getTime(training.duration)
                kcalHistory.text = training.kcal.toString()

            }
        }
    }

}