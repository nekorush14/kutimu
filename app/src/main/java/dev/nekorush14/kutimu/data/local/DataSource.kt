package dev.nekorush14.kutimu.data.local

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.Task
import dev.nekorush14.kutimu.data.Habit
import dev.nekorush14.kutimu.data.HabitCategory
import dev.nekorush14.kutimu.data.HabitFrequency
import dev.nekorush14.kutimu.data.Task
import dev.nekorush14.kutimu.data.TaskCategory
import dev.nekorush14.kutimu.data.TaskFrequency

/**
 * Local data source.
 *
 * ONLY USE FOR EARLY STAGE OF DEVELOPMENT.
 */
object DataSource {
    val habits = listOf(
        Habit(
            id = 1,
            title = "sample",
            description = "sample description",
            category = HabitCategory.Inbox,
            categoryIcon = Icons.Filled.Inbox,
            isPined = false,
            frequency = HabitFrequency.Weekly,
            completeCount = 1,
        ),
        Habit(
            id = 2,
            title = "sample 2",
            description = "sample description 2",
            category = HabitCategory.Task,
            categoryIcon = Icons.Filled.Task,
            isPined = false,
            frequency = HabitFrequency.Weekly,
            completeCount = 1,
        )
    )

    val tasks = listOf(
        Task(
            id = 1,
            title = "sample",
            details = "sample details",
            category = TaskCategory.Inbox,
            categoryIcon = Icons.Filled.Inbox,
            frequency = TaskFrequency.Daily,
        ),
        Task(
            id = 2,
            title = "sample 2",
            details = "Details of task description for this task. Current task is normal status. For more information, please send email.",
            category = TaskCategory.Inbox,
            categoryIcon = Icons.Filled.Inbox,
            frequency = TaskFrequency.Daily,
        ),
        Task(
            id = 3,
            title = "sample 3",
            details = "Details of task description for this task. Current task is normal status. For more information, please send email.",
            category = TaskCategory.Inbox,
            categoryIcon = Icons.Filled.Inbox,
            frequency = TaskFrequency.Daily,
            linkedHabit = Habit(
                id = 1,
                title = "Habit title",
                description = "Description",
                categoryIcon = Icons.Filled.Inbox,
                completeCount = 3,
                frequency = HabitFrequency.Weekly,
                category = HabitCategory.Inbox
            )
        )
    )
}