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

class ExerciseAdapter(var context: Context) : RecyclerView.Adapter<ExerciseAdapter.Holder>() {
    private var exerciseList = listOf<Exercise>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: ExerciseAdapter.Holder, position: Int) {
        holder.bind(exerciseList[position])
    }

    override fun getItemCount(): Int = exerciseList.size

    fun setExerciseList(list : List<Exercise>){
        exerciseList = list
    }

    private fun setInfo(exercise: Exercise, binding: ExerciseItemBinding) {
        Glide.with(context).load(exercise.imageUrl).load(binding.exerciseIV)
        binding.exerciseNameTV.text = exercise.name
        if (exercise.repeats == null) {
            binding.measureTV.text = exercise.workTime.toString()
        } else binding.measureTV.text = exercise.repeats.toString()
    }

    inner class Holder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ExerciseItemBinding.bind(item)
        fun bind(exercise: Exercise) {
            setInfo(exercise, binding)
        }
    }


}