package dev.nekorush14.kutimu.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import dev.nekorush14.kutimu.R
import dev.nekorush14.kutimu.data.Habit
import dev.nekorush14.kutimu.data.local.DataSource
import dev.nekorush14.kutimu.ui.component.HabitCard
import dev.nekorush14.kutimu.ui.theme.KutimuTheme

/**
 * All habits screen composable.
 *
 * Display all [Habit] as cards with [HabitCard].
 * [ExtendedFloatingActionButton] is displayed at the
 * bottom right corner to add a new habit.
 *
 * @param habits List of habits to be displayed.
 * @param onHabitClick Callback to be invoked when a habit card is clicked.
 * @param onPinClick Callback to be invoked when the pin icon is clicked.
 * @param onFabClick Callback to be invoked when the FAB is clicked.
 * @param modifier Modifier to be applied to the screen contents.
 */
@Composable
fun AllHabitsScreen(
    habits: List<Habit> = emptyList(),
    onHabitClick: () -> Unit,
    onPinClick: (Long) -> Unit,
    onFabClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box (
        modifier = modifier.fillMaxSize()
    ) {
        Column (
            modifier = modifier.padding(
                start = dimensionResource(R.dimen.list_padding_small),
                end = dimensionResource(R.dimen.list_padding_small)
            )
        ) {
            Text(
                text = stringResource(R.string.message_current_habits),
                style = TextStyle(
                    fontWeight = MaterialTheme.typography.displaySmall.fontWeight,
                    fontSize = MaterialTheme.typography.displaySmall.fontSize
                ),
                modifier = Modifier.padding(
                    start = dimensionResource(R.dimen.text_padding_small),
                    bottom = dimensionResource(R.dimen.text_padding_small)
                )
            )
            LazyColumn {
                items(habits) { habit ->
                    HabitCard(
                        habit = habit,
                        onHabitClick = onHabitClick,
                        onPinClick = onPinClick,
                        isAllHabitScreen = true,
                    )
                }
            }
        }
        ExtendedFloatingActionButton(
            onClick = onFabClick,
            containerColor = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(dimensionResource(R.dimen.fab_padding))
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = stringResource(R.string.content_description_button_add_habit)
            )
            Text(
                text = stringResource(R.string.label_button_add_habit),
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.label_padding_medium))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun AllHabitsScreenPreview() {
    KutimuTheme {
        AllHabitsScreen(
            habits = DataSource.habits,
            onHabitClick = {},
            onPinClick = {},
            onFabClick = {}
        )
    }
}