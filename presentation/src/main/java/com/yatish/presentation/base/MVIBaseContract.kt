package com.yatish.presentation.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVIBaseContract<ViewState, ViewIntent, SideEffect> {

    val viewState: StateFlow<ViewState>
    val sideEffect: Flow<SideEffect>

    fun sendIntent(intent: ViewIntent)
    fun createInitialState(): ViewState
}