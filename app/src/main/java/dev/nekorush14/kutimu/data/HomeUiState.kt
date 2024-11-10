package dev.nekorush14.kutimu.data

/**
 * Data class that represents the current UI state in terms of [habits],
 * [tasks], and [remainingTaskCount] of [HomeScreen].
 */
data class HomeUiState (
    /** The list of [Habit]s. */
    val habits: List<Habit> = listOf(),
    /** The list of [Task]s. */
    val tasks: List<Task> = listOf(),
    /** The number of remaining tasks. */
    val remainingTaskCount: Int = 0,
)