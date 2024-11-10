package dev.nekorush14.kutimu.data

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Task data class.
 */
data class Task(
    /** Unique identifier for the task. */
    val id: Long,
    /** Title of the task. */
    val title: String,
    /** Description of the task. */
    val details: String,
    /** Category of the task. */
    val category: TaskCategory,
    /** Category icon image of the task. */
    val categoryIcon: ImageVector,
    /** Frequency of the task. */
    val frequency: TaskFrequency,
    /** Completion status of the task. */
    val isCompleted: Boolean = false,
    /** Linked habit for this task. */
    val linkedHabit: Habit? = null,
)
