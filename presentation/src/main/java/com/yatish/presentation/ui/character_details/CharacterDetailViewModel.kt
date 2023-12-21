package com.yatish.presentation.ui.character_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yatish.domain.usecase.GetCharacterByIdUseCase
import com.yatish.presentation.mapper.CharacterDetailMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val mapper: CharacterDetailMapper
) : ViewModel(), CharacterDetailsContract {

    override fun createInitialState(): CharacterDetailsContract.ViewState =
        CharacterDetailsContract.ViewState.Loading

    private val _state = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<CharacterDetailsContract.ViewState>
        get() = _state.asStateFlow()

    override fun sendIntent(intent: CharacterDetailsContract.ViewIntent) {
        when(intent) {
            is CharacterDetailsContract.ViewIntent.LoadData -> {
                fetchCharacterById(intent.id)
            }
        }
    }

    private fun fetchCharacterById(id: String) {
        viewModelScope.launch {
            getCharacterByIdUseCase(id).onSuccess {
                _state.emit(CharacterDetailsContract.ViewState.Success(mapper.map(it)))
            }.onFailure {
                _state.emit(CharacterDetailsContract.ViewState.Error(it))
            }
        }
    }
}