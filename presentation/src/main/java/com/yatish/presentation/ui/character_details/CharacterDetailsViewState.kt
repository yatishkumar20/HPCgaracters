package com.yatish.presentation.ui.character_details

import com.yatish.presentation.base.SideEffect
import com.yatish.presentation.base.ViewIntent
import com.yatish.presentation.base.ViewState
import com.yatish.presentation.model.CharacterDetailsUIModel
import com.yatish.presentation.ui.character_list.CharacterListViewIntent

sealed interface CharacterDetailsViewState: ViewState {
    object Loading: CharacterDetailsViewState
    class Success(val data: CharacterDetailsUIModel): CharacterDetailsViewState
    class Error(val throwable: Throwable): CharacterDetailsViewState
}

sealed interface CharacterDetailsViewIntent: ViewIntent {
    class LoadData(val id: String): CharacterDetailsViewIntent
    object NavigateBack: CharacterDetailsViewIntent
}

sealed interface CharacterDetailsSideEffect: SideEffect {
    object NavigateBack: CharacterDetailsSideEffect
}