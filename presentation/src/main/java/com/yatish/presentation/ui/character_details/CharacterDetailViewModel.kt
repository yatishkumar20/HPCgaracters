package com.yatish.presentation.ui.character_details

import androidx.lifecycle.viewModelScope
import com.yatish.domain.usecase.GetCharacterByIdUseCase
import com.yatish.presentation.base.BaseViewModel
import com.yatish.presentation.mapper.CharacterDetailMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val mapper: CharacterDetailMapper
) : BaseViewModel<CharacterDetailsViewState, CharacterDetailsViewIntent, CharacterDetailsSideEffect>() {

    private fun fetchCharacterById(id: String) {
        viewModelScope.launch {
            _state.emit(CharacterDetailsViewState.Loading)
            getCharacterByIdUseCase(id).onSuccess {
                _state.emit(CharacterDetailsViewState.Success(mapper.map(it)))
            }.onFailure {
                _state.emit(CharacterDetailsViewState.Error(it))
            }
        }
    }

    private fun navigateBack() {
        viewModelScope.launch {
            _sideEffect.emit(CharacterDetailsSideEffect.NavigateBack)
        }
    }

    override fun sendIntent(intent: CharacterDetailsViewIntent) {
        when(intent) {
            is CharacterDetailsViewIntent.LoadData -> {
                fetchCharacterById(intent.id.toString())
            }

            is CharacterDetailsViewIntent.NavigateBack -> {
                navigateBack()
            }
        }
    }

}