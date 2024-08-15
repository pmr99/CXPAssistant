package com.google.ai.cxpAssistant.homepage

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class homepageViewModel: ViewModel() {
    private val _uiState: MutableStateFlow<homepageUIState> = MutableStateFlow(homepageUIState())
    val uiState: StateFlow<homepageUIState> = _uiState.asStateFlow()

    fun goToHomePage() {
        _uiState.update{state ->
            state.copy(
                page = Page.HOME
            )
        }
    }

    fun goToChatPage() {
        _uiState.update{ state ->
            state.copy (
                page = Page.CHAT
            )
        }
    }
}
