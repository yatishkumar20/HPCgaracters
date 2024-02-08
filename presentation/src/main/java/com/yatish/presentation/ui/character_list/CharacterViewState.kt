package com.yatish.presentation.ui.character_list

import com.yatish.presentation.base.MVISubContract
import com.yatish.presentation.base.SideEffect
import com.yatish.presentation.base.ViewIntent
import com.yatish.presentation.base.ViewState
import com.yatish.presentation.model.CharacterItemUIModel

interface CharacterListContract :
    MVISubContract<ViewState, ViewIntent, SideEffect> {
    sealed interface ListScreenViewState : ViewState {
        object Loading : ListScreenViewState
        data class Success(val data: List<CharacterItemUIModel>) : ListScreenViewState
        data class Error(val throwable: Throwable) : ListScreenViewState
    }

    sealed interface ListScreenViewIntent : ViewIntent {
        object LoadData : ListScreenViewIntent
        data class OnCharacterItemClick(val id: String, val name: String) : ListScreenViewIntent
    }

    sealed interface ListScreenSideEffect : SideEffect {
        data class NavigateToDetails(val id: String, val name: String) : ListScreenSideEffect
    }
}
