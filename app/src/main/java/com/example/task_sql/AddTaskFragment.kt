package com.example.task_sql

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider


class AddTaskFragment:Fragment() {
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_task_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        val nameInput: EditText = view.findViewById(R.id.taskInputField)
        val addButton:Button = view.findViewById(R.id.addButton)

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            viewModel.add(name, false)
            parentFragmentManager.popBackStack()
        }
    }

}