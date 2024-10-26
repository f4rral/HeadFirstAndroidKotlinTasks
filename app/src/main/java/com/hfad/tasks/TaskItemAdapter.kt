package com.hfad.tasks

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.tasks.databinding.TaskItemBinding


class TaskItemAdapter : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    // Создаёт новый объект ViewHolder всякий раз, когда RecyclerView нуждается в этом.
    // Это тот момент, когда создаётся layout строки списка, передается объекту ViewHolder,
    // и каждый дочерний view-компонент может быть найден и сохранен.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        Log.i("MyLog", "onCreateViewHolder")

        return TaskItemViewHolder.inflateFrom(parent)
    }

    // Принимает объект ViewHolder и устанавливает необходимые данные
    // для соответствующей строки во view-компоненте.
    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        Log.i("MyLog", "onBindViewHolder")

        val item = getItem(position)
        holder.bind(item)
    }

    // Держатель представления
    class TaskItemViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                Log.i("MyLog", "TaskItemViewHolder inflateFrom")

                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)

                return TaskItemViewHolder(binding)
            }
        }

        fun bind(item: Task) {
            Log.i("MyLog", "TaskItemViewHolder bind")

            binding.task = item
        }
    }
}