package dev.nekorush14.kutimu.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import dev.nekorush14.kutimu.R
import dev.nekorush14.kutimu.data.Habit
import dev.nekorush14.kutimu.data.HabitCategory
import dev.nekorush14.kutimu.data.HabitFrequency
import dev.nekorush14.kutimu.data.Task
import dev.nekorush14.kutimu.data.TaskCategory
import dev.nekorush14.kutimu.data.TaskFrequency
import dev.nekorush14.kutimu.data.local.DataSource
import dev.nekorush14.kutimu.ui.theme.KutimuTheme

/**
 * Home screen composable.
 *
 * @param habits List of habits to be displayed.
 * @param tasks List of tasks to be displayed.
 * @param greetingMessage Greeting message to be displayed.
 * @param modifier Modifier to be applied to the screen contents.
 */
@Composable
fun HomeScreen(
    habits: List<Habit> = emptyList(),
    tasks: List<Task> = emptyList(),
    greetingMessage: String = "",
    modifier: Modifier = Modifier
) {
    val remainingTaskCount =
        tasks.count { task -> !task.isCompleted && task.frequency == TaskFrequency.Daily }

    Column(
        modifier = modifier.padding(dimensionResource(R.dimen.list_padding_small))
    ) {
        Text(
            text = greetingMessage,
            style = TextStyle(
                fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                fontSize = MaterialTheme.typography.displaySmall.fontSize
            ),
            modifier = Modifier.padding(
                start = dimensionResource(R.dimen.text_padding_small),
                bottom = dimensionResource(R.dimen.text_padding_small)
            )
        )
        // Habit block
        if (habits.isNotEmpty()) {
            repeat(habits.size) { index ->
                HabitCard(habit = habits[index])
            }
        } else {
            Surface(
                color = MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.card_padding_small))
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(dimensionResource(R.dimen.box_height_medium))
                        .padding(dimensionResource(R.dimen.list_padding_small))
                ) {
                    Text(
                        text = stringResource(R.string.message_no_pinned_habits),
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                            fontSize = MaterialTheme.typography.bodyMedium.fontSize
                        ),
                    )
                }
            }
        }
        // Upcoming tracking tasks block
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = dimensionResource(R.dimen.card_padding_medium),
                    bottom = dimensionResource(R.dimen.card_padding_medium)
                )
        ) {
            Text(
                text = stringResource(R.string.message_up_coming_tracking_tasks),
                style = TextStyle(
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            )
            Text(
                text = if (remainingTaskCount > 0) stringResource(
                    R.string.message_today_tasks_remaining,
                    remainingTaskCount
                ) else stringResource(R.string.message_no_tasks_remaining),
                style = TextStyle(
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            )
        }
        // Task list block
        if (tasks.isNotEmpty()) {
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                items(tasks.size) { index ->
                    TaskListItem(task = tasks[index])
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(top = dimensionResource(R.dimen.list_padding_small))
                    )
                }
            }
        } else {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(dimensionResource(R.dimen.list_padding_small))
            ) {
                Text(
                    text = stringResource(R.string.message_all_tasks_completed),
                    textAlign = TextAlign.Center,
                    style = TextStyle(
                        fontWeight = MaterialTheme.typography.bodyMedium.fontWeight,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    ),
                )
            }
        }
    }
}

/**
 * Habit card composable.
 *
 * To grans a habit, card show [Habit.categoryIcon], [Habit.title],
 * and completion status based on [Habit.frequency].
 *
 * @param habit Habit to be displayed.
 * @param frequencyMaxCount Maximum count of status icons to display.
 * @param modifier Modifier to be applied to the card.
 */
@Composable
fun HabitCard(
    habit: Habit,
    frequencyMaxCount: Int = 7,
    modifier: Modifier = Modifier
) {
    Card(
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.card_elevation_default)
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.card_padding_small))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.card_padding_medium))
        ) {
            Icon(
                imageVector = habit.categoryIcon,
                contentDescription = null,
                modifier = Modifier
                    .size(dimensionResource(R.dimen.icon_size_medium))
                    .padding(end = dimensionResource(R.dimen.card_padding_small))
            )
            Column {
                Text(
                    text = habit.title,
                    style = TextStyle(
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = dimensionResource(R.dimen.card_padding_small))
                ) {
                    repeat(habit.completeCount) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                        )
                    }
                    repeat(frequencyMaxCount - habit.completeCount) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = null,
                        )
                    }
                }
            }
        }
    }
}

/**
 * Task list item composable.
 *
 * To display task, each item show [Task.categoryIcon], [Task.title],
 * and [Task.details]. If task is linked to habit, it will show
 * [Habit.title] at above the [Task.title]
 *
 * @param task Task to be displayed.
 * @param onCheckedChange Callback to be invoked when the checkbox is checked.
 * @param modifier Modifier to be applied to the List item.
 */
@Composable
fun TaskListItem(
    task: Task,
    onCheckedChange: (Boolean) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                top = dimensionResource(R.dimen.list_padding_small),
                bottom = dimensionResource(R.dimen.list_padding_small)
            )
    ) {
        Icon(
            imageVector = task.categoryIcon,
            contentDescription = null,
            modifier = Modifier
                .size(dimensionResource(R.dimen.icon_size_small))
                .padding(end = dimensionResource(R.dimen.card_padding_small))
        )
        Column {
            if (task.linkedHabit != null) {
                Text(
                    text = task.linkedHabit.title,
                    style = TextStyle(
                        fontWeight = MaterialTheme.typography.titleSmall.fontWeight,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    ),
                    modifier = Modifier.padding(top = dimensionResource(R.dimen.text_padding_small))
                )
            }
            Text(
                text = task.title,
                style = TextStyle(
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                ),
                modifier = Modifier.padding(top = dimensionResource(R.dimen.text_padding_small))
            )
            Text(
                text = task.details,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = TextStyle(
                    lineBreak = LineBreak(
                        strategy = LineBreak.Strategy.HighQuality,
                        strictness = LineBreak.Strictness.Strict,
                        wordBreak = LineBreak.WordBreak.Phrase,
                    )
                ),
                modifier = Modifier
                    .wrapContentSize()
                    .padding(top = dimensionResource(R.dimen.card_padding_small))
                    .width(dimensionResource(R.dimen.text_width_large))
            )
        }
        Spacer(Modifier.weight(1f))
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange,
            modifier = Modifier.align(alignment = Alignment.CenterVertically)
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    KutimuTheme {
        HomeScreen(
            habits = DataSource.habits,
            tasks = DataSource.tasks,
            greetingMessage = "Good morning!"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenWithoutHabitAndTaskPreview() {
    KutimuTheme {
        HomeScreen(
            habits = emptyList(),
            tasks = emptyList(),
            greetingMessage = "Good morning!"
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HabitCardPreview() {
    KutimuTheme {
        HabitCard(
            habit = Habit(
                id = 1,
                title = "Title",
                description = "Description",
                categoryIcon = Icons.Filled.Inbox,
                completeCount = 3,
                frequency = HabitFrequency.Weekly,
                category = HabitCategory.Inbox
            ),
            frequencyMaxCount = 7
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TaskListItemPreview() {
    KutimuTheme {
        TaskListItem(
            task = Task(
                id = 1,
                title = "Title",
                details = "Details of task description for this task. Current task is normal status. For more information, please send email.",
                category = TaskCategory.Inbox,
                categoryIcon = Icons.Filled.Inbox,
                isCompleted = true,
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
}