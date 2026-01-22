package com.example.week1kotlinperusteet.viewmodel

import androidx.lifecycle.ViewModel
import com.example.week1kotlinperusteet.domain.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    fun addTask(task: Task) {
        _tasks.value = _tasks.value + task
    }

    fun toggleDone(id: Int) {
        _tasks.value = _tasks.value.map { task ->
            if (task.id == id) {
                task.copy(done = !task.done)
            } else {
                task
            }
        }
    }

    fun removeTask(id: Int) {
        _tasks.value = _tasks.value.filterNot { it.id == id }
    }

    fun filterByDone(done: Boolean) {
        _tasks.value = _tasks.value.filter { it.done == done }
    }

    fun sortByDueDate() {
        _tasks.value = _tasks.value.sortedBy { it.dueDate }
    }
}