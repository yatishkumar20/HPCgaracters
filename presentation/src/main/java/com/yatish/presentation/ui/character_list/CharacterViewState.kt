package com.yatish.presentation.ui.character_list

import com.yatish.presentation.base.MVIBaseContract
import com.yatish.presentation.model.CharacterItemUIModel

interface CharacterListContract :
    MVIBaseContract<CharacterListContract.ViewState, CharacterListContract.ViewIntent, CharacterListContract.SideEffect> {
    sealed interface ViewState {
        object Loading : ViewState
        class Success(val data: List<CharacterItemUIModel>) : ViewState
        class Error(val throwable: Throwable) : ViewState
    }

    sealed interface ViewIntent {
        object LoadData : ViewIntent
        class OnCharacterItemClick(val id: String, val name: String) : ViewIntent
    }

    sealed interface SideEffect {
        class NavigateToDetails(val id: String, val name: String) : SideEffect
    }
}
