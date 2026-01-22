package com.example.week1kotlinperusteet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.week1kotlinperusteet.ui.HomeScreen
import com.example.week1kotlinperusteet.viewmodel.TaskViewModel
import com.example.week1kotlinperusteet.domain.Task

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: TaskViewModel = viewModel()
            val tasks by viewModel.tasks.collectAsState()

            HomeScreen(
                tasks = tasks,
                onToggleDone = { viewModel.toggleDone(it) },
                onRemove = { viewModel.removeTask(it) },
                onAddTask = { title ->
                    viewModel.addTask(
                        Task(
                            id = tasks.size + 1,
                            title = title
                        )
                    )
                },
                onFilterDone = { done ->
                    viewModel.filterByDone(done)
                },
                onSortByDueDate = {
                    viewModel.sortByDueDate()
                }
            )

        }
    }
}