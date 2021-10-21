package com.example.melnyk_sportea_app.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.ExerciseItemBinding
import com.example.melnyk_sportea_app.model.Exercise
import com.example.melnyk_sportea_app.utils.TimeFormatter

class ExerciseAdapter(
    var context: Context,
    var clickListener: OnItemClickListener
) :
    RecyclerView.Adapter<ExerciseAdapter.Holder>() {
    private var exerciseList = listOf<Exercise>()
    private val timeFormatter = TimeFormatter()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return Holder(view, clickListener)
    }

    override fun onBindViewHolder(holder: ExerciseAdapter.Holder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int = exerciseList.size

    fun setExerciseList(list: List<Exercise>) {
        exerciseList = list
    }

    private fun setInfo(exercise: Exercise, binding: ExerciseItemBinding) {
        Glide.with(context).load(exercise.imageUrl).centerCrop().into(binding.exerciseIV)
        binding.exerciseNameTV.text = exercise.name
        if (exercise.repeats == 0) {
            binding.measureTV.text = timeFormatter.getTime(exercise.workTime!!)
        } else binding.measureTV.text = "x${exercise.repeats}"
    }

    inner class Holder(var item: View, var clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(item), View.OnClickListener {
        private val binding = ExerciseItemBinding.bind(item)

        fun bind(exercise: Exercise) {
            item.setOnClickListener(this)
            setInfo(exercise, binding)
        }

        override fun onClick(p0: View?) {
            clickListener.onItemClick(adapterPosition)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}