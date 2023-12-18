package com.yatish.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


abstract class BaseViewModel<VS : ViewState, VI : ViewIntent, SE : SideEffect> : ViewModel() {

    protected val state = MutableSharedFlow<VS>()
    val _state = state.asSharedFlow()

    protected val sideEffect = MutableSharedFlow<SE>()
    val _sideEffect = sideEffect.asSharedFlow()

    abstract fun sendIntent(intent: VI)
}