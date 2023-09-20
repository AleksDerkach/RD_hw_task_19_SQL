package com.example.task_sql

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(private var items: List<Task> = emptyList()) :RecyclerView.Adapter<TaskViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val listItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return TaskViewHolder(listItemView)
     }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.task.text = items[position].task
        holder.checkFlag.isChecked = items[position].doneFlag
        holder.checkFlag.setOnClickListener {
            TaskViewModel().update(Task(items[position].id, items[position].task, holder.checkFlag.isChecked))
        }
        holder.delBtn.setOnClickListener {
            TaskViewModel().remove(Task(items[position].id, items[position].task, holder.checkFlag.isChecked))
            notifyDataSetChanged()
        }
    }

    fun updateItems(itemsToUpdate: List<Task>) {
        items = itemsToUpdate
        notifyDataSetChanged()
    }

}

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    //var taskId: EditText = itemView.findViewById(R.id.taskId)
    val task: TextView = itemView.findViewById(R.id.task)
    val checkFlag: CheckBox = itemView.findViewById(R.id.checkbox)
    val delBtn: Button = itemView.findViewById(R.id.delButton)
}
