package com.example.melnyk_sportea_app.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.melnyk_sportea_app.R
import com.example.melnyk_sportea_app.databinding.ProgramItemBinding
import com.example.melnyk_sportea_app.databinding.ProgramLevelBinding
import com.example.melnyk_sportea_app.model.TrainingProgram

class TrainingProgramAdapter(var context: Context, var clickListener: OnItemClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val LEVEL = 0
    private val PROGRAM = 1

    private var programList = listOf<TrainingProgram>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == PROGRAM) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.program_item, parent, false)
            ProgramHolder(item = view, clickListener)
        } else {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.program_level, parent, false)
            LevelHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == PROGRAM) {
            (holder as ProgramHolder).bind(programList[position])
        } else (holder as LevelHolder).bind(programList[position])
    }

    override fun getItemCount(): Int = programList.size

    fun setProgramList(list: List<TrainingProgram>) {
        programList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return if (programList[position].level != null) {
            LEVEL
        } else PROGRAM
    }

    inner class ProgramHolder(var item: View, var clickListener: OnItemClickListener) :
        RecyclerView.ViewHolder(item), View.OnClickListener {
        private val binding = ProgramItemBinding.bind(item)
        fun bind(program: TrainingProgram) {
            item.setOnClickListener(this)
            binding.programNameTV.text = program.programName
            Glide.with(binding.imageView).load(program.imageUrl).centerCrop()
                .into(binding.imageView)
        }

        override fun onClick(p0: View?) {
            clickListener.onItemClick(adapterPosition)
        }
    }

    inner class LevelHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ProgramLevelBinding.bind(item)
        fun bind(program: TrainingProgram) {
            binding.textView.text = context.resources.getString(program.level?.res!!)
        }
    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }
}