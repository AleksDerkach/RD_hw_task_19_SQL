package com.example.task_sql

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment: Fragment() {
    lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = TaskAdapter()

        viewModel = ViewModelProvider(requireActivity()).get(TaskViewModel::class.java)
        val fab:FloatingActionButton = view.findViewById(R.id.fab)
        val listView:RecyclerView = view.findViewById(R.id.list)

        listView.adapter = adapter
        listView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.listState.observe(viewLifecycleOwner) {
            uiState ->
            when(uiState){
                is TaskViewModel.ListState.EmptyList -> Unit
                is TaskViewModel.ListState.UpdatedList -> {
                    adapter.updateItems(uiState.list)
                }

            }
        }

        // при натискані повинен завантажитись інший фрагиент
        fab.setOnClickListener {
            val fragment = AddTaskFragment()
            parentFragmentManager.beginTransaction()
                .add(R.id.container,fragment)
                .addToBackStack(fragment.javaClass.name)
                .commit()
        }

    }

}