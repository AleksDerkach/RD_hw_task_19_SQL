package com.example.task_sql

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel

class TaskViewModel:ViewModel() {
    private val repo = MyApp.getApp().repo
    private val _listState = MutableLiveData<ListState>(ListState.EmptyList)
    val listState: LiveData<ListState> = _listState
    private val observer = Observer<List<Task>>{
        _listState.postValue(ListState.UpdatedList(list = it))
    }

    init{
        repo.getAll().observeForever(observer)
    }

    fun add(name:String, doneFlag: Boolean) {
        repo.add(Task(task = name, doneFlag = doneFlag))
    }

    fun remove(task: Task){
        repo.remove(task)
    }

    fun update(task: Task){
        repo.update(task)
    }

    override  fun onCleared(){
        super.onCleared()
        repo.getAll().removeObserver(observer)
    }


    sealed class ListState {
        object EmptyList : ListState()
        // сюди прилітає новий список
        class UpdatedList(val list:List<Task>):ListState()
    }
}