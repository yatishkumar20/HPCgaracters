package com.yatish.presentation.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow


abstract class BaseViewModel<VS : ViewState, VI : ViewIntent, SE : SideEffect> : ViewModel() {

    protected val _state = MutableSharedFlow<VS>()
    val state = _state.asSharedFlow()

    protected val _sideEffect = MutableSharedFlow<SE>()
    val sideEffect = _sideEffect.asSharedFlow()

    abstract fun sendIntent(intent: VI)
}