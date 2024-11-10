package dev.nekorush14.kutimu.data

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Habit data class.
 */
data class Habit(
    /** Unique ID of the habit. */
    val id: Long,
    /** Title of the habit. */
    val title: String,
    /** Description of the habit. */
    val description: String,
    /** Frequency of the habit. */
    val frequency: HabitFrequency,
    /** Icon of the habit. */
    val category: HabitCategory,
    /** Icon image of the habit. */
    val categoryIcon: ImageVector,
    /** Count of completed sub-tasks for this habit. */
    val completeCount: Int,
    /** Pin status of the habit. */
    val isPined: Boolean = false,
    /** Completion status of the habit. */
    val isCompleted: Boolean = false,
    /** Sub-tasks of the habit. */
    val linkedTasks: List<Task> = emptyList()
)
