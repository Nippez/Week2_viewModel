package com.example.week1kotlinperusteet.domain

import java.time.LocalDate

data class Task(
    val id: Int,
    val title: String,
    val description: String = "",
    val priority: Int = 0,
    val done: Boolean = false,
    val dueDate: LocalDate = LocalDate.now(),
)
