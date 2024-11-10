package dev.nekorush14.kutimu.ui.viewmodel

import androidx.lifecycle.ViewModel
import dev.nekorush14.kutimu.R
import dev.nekorush14.kutimu.data.HomeUiState
import dev.nekorush14.kutimu.data.local.DataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class KutimuViewModel : ViewModel() {

    /**
     * Kutimu state
     */
    private val _uiState = MutableStateFlow(HomeUiState())

    /** Kutimu app UI state */
    val uiState = _uiState.asStateFlow()

    init {
        initializeUiState()
    }

    /**
     * Initialize UI state with default values
     */
    private fun initializeUiState() {
        _uiState.value = HomeUiState(
            habits = DataSource.habits,
            tasks = DataSource.tasks,
            remainingTaskCount = DataSource.tasks.size - DataSource.tasks.count { it.isCompleted },
            greetingMessage = getGreetingMessage()
        )
    }

    /**
     * Get greeting message based on the current time
     *
     * @return Greeting message string resource ID
     */
    private fun getGreetingMessage(): Int {
        // Return greeting message based on the current time
        val currentTime = System.currentTimeMillis()
        return when {
            currentTime < 12 * 60 * 60 * 1000 -> R.string.message_good_morning
            currentTime < 18 * 60 * 60 * 1000 -> R.string.message_good_afternoon
            else -> R.string.message_good_evening
        }
    }

    /**
     * Update greeting message in UI state
     */
    fun updateGreetingMessage() {
        _uiState.value = _uiState.value.copy(
            greetingMessage = getGreetingMessage()
        )
    }
}