package com.yatish.presentation.ui.character_list

import com.yatish.presentation.base.SideEffect
import com.yatish.presentation.base.ViewIntent
import com.yatish.presentation.base.ViewState
import com.yatish.presentation.model.CharacterItemUIModel

sealed interface CharacterListViewState: ViewState {
    object Loading: CharacterListViewState
    class Success(val data: List<CharacterItemUIModel>): CharacterListViewState
    class Error(val throwable: Throwable): CharacterListViewState
}

sealed interface CharacterListViewIntent: ViewIntent {
    object LoadData: CharacterListViewIntent
    class OnCharacterItemClick(val id: String): CharacterListViewIntent
}

sealed interface CharacterListSideEffect: SideEffect {
    class NavigateToDetails(val id: String): CharacterListSideEffect
}
