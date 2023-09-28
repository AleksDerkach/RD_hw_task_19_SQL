package com.example.task_sql

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter(val viewModel: TaskViewModel, private var items: List<Task> = emptyList()) :RecyclerView.Adapter<TaskViewHolder>(){

    //lateinit var viewModel: TaskViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val listItemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        //viewModel =  ViewModelProvider().get(TaskViewModel::class.java)//ViewModelProvider(activity).get(TaskViewModel::class.java)
        return TaskViewHolder(listItemView)
     }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        //holder.task.text = items[position].task
        holder.task.text = items[holder.adapterPosition].task
        holder.checkFlag.setOnClickListener {
            // Це перше рішення -
            //viewModel.update(Task(items[position].id, items[position].task, holder.checkFlag.isChecked))

            // це друге рішення
             items[holder.adapterPosition].doneFlag = holder.checkFlag.isChecked
            viewModel.update(items[holder.adapterPosition])
        }
        holder.checkFlag.isChecked = items[holder.adapterPosition].doneFlag
        holder.delBtn.setOnClickListener {
            // Це перше рішення -
            //viewModel.remove(Task(items[position].id, items[position].task, holder.checkFlag.isChecked))

            // це друге рішення
            viewModel.remove(items[position])
            notifyDataSetChanged()
        }
    }

    fun updateItems(itemsToUpdate: List<Task>) {
        items = itemsToUpdate
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)

    }

}

class TaskViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    //var taskId: EditText = itemView.findViewById(R.id.taskId)
    val task: TextView = itemView.findViewById(R.id.task)
    val checkFlag: CheckBox = itemView.findViewById(R.id.checkbox)
    val delBtn: Button = itemView.findViewById(R.id.delButton)
}
