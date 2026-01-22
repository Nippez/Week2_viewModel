package com.example.week1kotlinperusteet.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.week1kotlinperusteet.domain.Task

@Composable
fun HomeScreen(
    tasks: List<Task>,
    onToggleDone: (Int) -> Unit,
    onRemove: (Int) -> Unit,
    onAddTask: (String) -> Unit,
    onFilterDone: (Boolean) -> Unit,
    onSortByDueDate: () -> Unit,
)
{
    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(16.dp)) {

        Text("Tehtävälista", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier
            .height(16.dp))

        Row {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                modifier = Modifier
                    .weight(1f),
                placeholder = { Text("Uusi tehtävä") }
            )

            Spacer(modifier = Modifier
                .width(8.dp))
            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    onAddTask(newTaskTitle)
                    newTaskTitle = ""
                }
            })
            {
                Text("Lisää")
            }
        }
        Spacer(modifier = Modifier
            .height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        )
        {
            Button(
                onClick = { onFilterDone(true) },
                modifier = Modifier
                    .weight(1f)
            )
            {
                Text("Valmiit")
            }

            Button(
                onClick = { onFilterDone(false)},
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Kesken")
            }
        }

        Spacer(modifier = Modifier
            .height(8.dp))

        Button(
            onClick = { onSortByDueDate() },
            modifier = Modifier
                .fillMaxWidth()
        )
        {
            Text("Järjestä päivän mukan")
        }


        Spacer(modifier = Modifier
            .height(16.dp))

        LazyColumn {
            items(tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                )
                {
                    Row {
                        Checkbox(
                            checked = task.done,
                            onCheckedChange = { onToggleDone(task.id) }
                        )
                        Text(task.title)
                    }

                    Button(onClick = { onRemove(task.id) }) {
                        Text("Poista")
                    }
                }
            }
        }
    }
}