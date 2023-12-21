package com.yatish.presentation.base

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface MVIBaseContract<ViewState, ViewIntent> {

    val viewState: StateFlow<ViewState>

    fun sendIntent(intent: ViewIntent)
    fun createInitialState(): ViewState
}

interface MVISubContract<ViewState, ViewIntent, SideEffect>: MVIBaseContract<ViewState, ViewIntent> {
    override val viewState: StateFlow<ViewState>
    val sideEffect: Flow<SideEffect>

    override fun sendIntent(intent: ViewIntent)
    override fun createInitialState(): ViewState
}