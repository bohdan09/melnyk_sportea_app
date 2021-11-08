package com.example.melnyk_sportea_app.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.HistoryItemBinding
import com.example.melnyk_sportea_app.model.TrainingJournal
import com.example.melnyk_sportea_app.utils.TimeFormatter

class HistoryAdapter(
    var context: Context,
    val listener: OnItemClickListener
) : RecyclerView.Adapter<HistoryAdapter.Holder>() {
    private var trainingJournal = listOf<TrainingJournal>()
    private val timeFormatter = TimeFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return Holder(view, listener)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(trainingJournal[position])
    }

    override fun getItemCount(): Int = trainingJournal.size

    fun setTrainingJournal(journal: List<TrainingJournal>) {
        trainingJournal = journal
        notifyDataSetChanged()
    }

    inner class Holder(val item: View, val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(item), View.OnClickListener {

        val binding = HistoryItemBinding.bind(item)
        fun bind(training: TrainingJournal) {
            item.setOnClickListener(this)
            with(binding) {
                Glide.with(context).load(training.imageUrl).centerCrop().into(historyItem)
                historyDate.text = timeFormatter.getDate(training.date)
                programName.text = training.programName
                durationHistory.text = timeFormatter.getTime(training.duration)
                kcalHistory.text = training.kcal.toString()

            }
        }

        override fun onClick(p0: View?) {
            listener.onItemClick(adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

}