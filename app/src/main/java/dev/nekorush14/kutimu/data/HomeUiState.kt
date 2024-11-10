package dev.nekorush14.kutimu.data

import dev.nekorush14.kutimu.R
import dev.nekorush14.kutimu.ui.KutimuAppScreen

/**
 * Data class that represents the current UI state in terms of [habits],
 * [tasks], and [remainingTaskCount] of [KutimuAppScreen.Home].
 */
data class HomeUiState (
    /** The list of [Habit]s. */
    val habits: List<Habit> = listOf(),
    /** The list of [Task]s. */
    val tasks: List<Task> = listOf(),
    /** The number of remaining tasks. */
    val remainingTaskCount: Int = 0,
    /** The message to be displayed as top message. */
    val greetingMessage: Int = R.string.message_good_morning,
)