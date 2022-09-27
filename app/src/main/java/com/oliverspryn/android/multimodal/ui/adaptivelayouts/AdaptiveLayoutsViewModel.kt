package com.oliverspryn.android.multimodal.ui.adaptivelayouts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class AdaptiveLayoutsViewModel : ViewModel() {

    private val viewModelState = MutableStateFlow(AdaptiveLayoutsUiState())

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            AdaptiveLayoutsUiState()
        )

    fun closeDetails() = viewModelState.unselectNumber()
    fun openDetails(selectedNumber: Int) = viewModelState.selectNumber(selectedNumber)

    private fun MutableStateFlow<AdaptiveLayoutsUiState>.selectNumber(selectedNumber: Int) = update {
        it.copy(
            numberSelected = true,
            numberFromList = selectedNumber
        )
    }

    private fun MutableStateFlow<AdaptiveLayoutsUiState>.unselectNumber() = update {
        it.copy(
            numberSelected = false,
            numberFromList = 0
        )
    }
}

data class AdaptiveLayoutsUiState(
    val numberSelected: Boolean = false,
    val numberFromList: Int = 0
)
