package app.mvi_developers.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.mvi_developers.data.DeveloperRepo
import app.mvi_developers.intent.DeveloperIntent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DeveloperViewModel @Inject constructor(private val developerRepo: DeveloperRepo) :
    ViewModel() {

    private val _state = MutableStateFlow(DeveloperViewState())
    val state: StateFlow<DeveloperViewState> get() = _state


    init {
        handleIntent(DeveloperIntent.FetchDevelopers)
    }


    fun handleIntent(intent: DeveloperIntent) {
        viewModelScope.launch {
            when (intent) {
                DeveloperIntent.FetchDevelopers -> getDevelopers()
            }
        }
    }

    private suspend fun getDevelopers() {
        try {
            _state.value = _state.value.copy(loading = true, error = null)
            val result = developerRepo.getDevelopers()
            _state.value = DeveloperViewState(loading = false, developers = result, error = null)
        } catch (e: Exception) {
            _state.value = DeveloperViewState(
                loading = false,
                error = e.message ?: "Error fetching developers"
            )
        }
    }


}