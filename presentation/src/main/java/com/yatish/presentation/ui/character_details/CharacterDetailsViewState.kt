package com.yatish.presentation.ui.character_details

import com.yatish.presentation.base.MVIBaseContract
import com.yatish.presentation.model.CharacterDetailsUIModel

interface CharacterDetailsContract: MVIBaseContract<CharacterDetailsContract.ViewState, CharacterDetailsContract.ViewIntent> {
    sealed interface ViewState {
        object Loading : ViewState
        class Success(val data: CharacterDetailsUIModel) : ViewState
        class Error(val throwable: Throwable) : ViewState
    }

    sealed interface ViewIntent {
        class LoadData(val id: String) : ViewIntent
    }
}