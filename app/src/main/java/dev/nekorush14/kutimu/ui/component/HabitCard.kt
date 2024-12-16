package dev.nekorush14.kutimu.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.PushPin
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.PushPin
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.nekorush14.kutimu.R
import dev.nekorush14.kutimu.data.Habit
import dev.nekorush14.kutimu.data.HabitCategory
import dev.nekorush14.kutimu.data.HabitFrequency
import dev.nekorush14.kutimu.ui.theme.KutimuTheme

/**
 * Habit card composable.
 *
 * To grans a habit, card show [Habit.categoryIcon], [Habit.title],
 * and completion status based on [Habit.frequency].
 *
 * @param habit Habit to be displayed.
 * @param onHabitClick Callback to be invoked when the habit card is clicked.
 * @param onPinClick Callback to be invoked when the pin button is clicked.
 * @param isAllHabitScreen Flag to indicate if the habit card is displayed in the all habits screen.
 * @param frequencyMaxCount Maximum count of status icons to display.
 * @param modifier Modifier to be applied to the card.
 */
@Composable
fun HabitCard(
    habit: Habit,
    onHabitClick: () -> Unit = {},
    onPinClick: (Long) -> Unit = {},
    isAllHabitScreen: Boolean = false,
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
            .clickable(enabled = isAllHabitScreen) {
                onHabitClick()
            }
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
                        .padding(top = dimensionResource(R.dimen.card_padding_small))
                ) {
                    // Show filled icons for completed count
                    repeat(habit.completeCount) {
                        Icon(
                            imageVector = Icons.Filled.CheckCircle,
                            contentDescription = null,
                        )
                    }
                    // Show outlined icons for remaining count
                    repeat(frequencyMaxCount - habit.completeCount) {
                        Icon(
                            imageVector = Icons.Outlined.CheckCircle,
                            contentDescription = null,
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            if (isAllHabitScreen && habit.isPined) {
                IconButton(
                    onClick = { onPinClick(habit.id) },
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.icon_size_medium))
                ) {
                    Icon(
                        imageVector = Icons.Filled.PushPin,
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.icon_size_medium))
                            .padding(end = dimensionResource(R.dimen.card_padding_small))
                    )
                }
            } else if (isAllHabitScreen) {
                IconButton(
                    onClick = { onPinClick(habit.id) },
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.icon_size_medium))
                ) {
                    Icon(
                        imageVector = Icons.Outlined.PushPin,
                        contentDescription = null,
                        modifier = Modifier
                            .size(dimensionResource(R.dimen.icon_size_medium))
                            .padding(end = dimensionResource(R.dimen.card_padding_small))
                    )
                }
            }
        }
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
private fun AllHabitsScreenPinedHabitCardPreview() {
    KutimuTheme {
        HabitCard(
            habit = Habit(
                id = 1,
                title = "Title",
                description = "Description",
                categoryIcon = Icons.Filled.Inbox,
                isPined = true,
                completeCount = 3,
                frequency = HabitFrequency.Weekly,
                category = HabitCategory.Inbox
            ),
            isAllHabitScreen = true,
            frequencyMaxCount = 7
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AllHabitScreenUnPinedHabitCardPreview() {
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
            isAllHabitScreen = true,
            frequencyMaxCount = 7
        )
    }
}