package com.yatish.presentation.ui.character_details

import com.yatish.presentation.base.MVIBaseContract
import com.yatish.presentation.base.ViewIntent
import com.yatish.presentation.base.ViewState
import com.yatish.presentation.model.CharacterDetailsUIModel

interface CharacterDetailsContract :
    MVIBaseContract<ViewState, ViewIntent> {
    sealed interface DetailScreenViewState : ViewState {
        object Loading : DetailScreenViewState
        data class Success(val data: CharacterDetailsUIModel) : DetailScreenViewState
        data class Error(val throwable: Throwable) : DetailScreenViewState
    }

    sealed interface DetailScreenViewIntent : ViewIntent {
        data class LoadData(val id: String) : DetailScreenViewIntent
    }
}