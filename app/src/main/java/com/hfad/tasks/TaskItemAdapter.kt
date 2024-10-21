package com.hfad.tasks

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskItemAdapter : RecyclerView.Adapter<TaskItemAdapter.TaskItemViewHolder>() {

    var data = listOf<Task>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    // Возвращает общее количество элементов списка
    override fun getItemCount() = data.size

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

        val item = data[position]
        holder.bind(item)
    }

    // Держатель представления
    class TaskItemViewHolder(val rootView: TextView) : RecyclerView.ViewHolder(rootView) {

        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                Log.i("MyLog", "TaskItemViewHolder inflateFrom")
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.task_item, parent, false) as TextView

                return TaskItemViewHolder(view)
            }
        }

        fun bind(item: Task) {
            Log.i("MyLog", "TaskItemViewHolder bind")

            rootView.text = item.taskName
        }
    }
}