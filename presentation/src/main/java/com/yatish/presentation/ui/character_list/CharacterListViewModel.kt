package com.yatish.presentation.ui.character_list

import androidx.lifecycle.viewModelScope
import com.yatish.domain.usecase.GetAllCharactersUseCase
import com.yatish.presentation.base.BaseViewModel
import com.yatish.presentation.mapper.CharacterItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
        private val getAllCharactersUseCase: GetAllCharactersUseCase,
        private val characterItemMapper: CharacterItemMapper
    ): BaseViewModel<CharacterListViewState, CharacterListViewIntent, CharacterListSideEffect>() {

    private fun fetchCharacterList() {
        viewModelScope.launch {
            state.emit(CharacterListViewState.Loading)
            getAllCharactersUseCase().onSuccess {
                state.emit(CharacterListViewState.Success(it.map { item -> characterItemMapper.map(item) }))
            }.onFailure {
                state.emit(CharacterListViewState.Error(it))
            }
        }
    }

    private fun navigateToDetails(id: String) {
        viewModelScope.launch {
            sideEffect.emit(CharacterListSideEffect.NavigateToDetails(id))
        }
    }

    override fun sendIntent(intent: CharacterListViewIntent) {
        when(intent) {
            is CharacterListViewIntent.LoadData -> fetchCharacterList()
            is CharacterListViewIntent.OnCharacterItemClick -> navigateToDetails(intent.id)
        }
    }

}