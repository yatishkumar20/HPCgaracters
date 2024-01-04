package com.yatish.presentation.ui.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yatish.domain.usecase.GetAllCharactersUseCase
import com.yatish.presentation.mapper.CharacterItemMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val characterItemMapper: CharacterItemMapper
) : ViewModel(), CharacterListContract {

    override fun createInitialState(): CharacterListContract.ViewState =
        CharacterListContract.ViewState.Loading

    private val _state = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<CharacterListContract.ViewState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<CharacterListContract.SideEffect>()
    override val sideEffect: SharedFlow<CharacterListContract.SideEffect>
        get() = _sideEffect.asSharedFlow()

    override fun sendIntent(intent: CharacterListContract.ViewIntent) {
        when (intent) {
            is CharacterListContract.ViewIntent.LoadData -> fetchCharacterList()
            is CharacterListContract.ViewIntent.OnCharacterItemClick -> navigateToDetails(
                intent.id,
                intent.name
            )
        }
    }

    private fun fetchCharacterList() {
        viewModelScope.launch {
            getAllCharactersUseCase().onSuccess { response ->
                _state.emit(CharacterListContract.ViewState.Success(response.filter { it.house != "" }
                    .map { item -> characterItemMapper.map(item) }))
            }.onFailure {
                _state.emit(CharacterListContract.ViewState.Error(it))
            }
        }
    }

    private fun navigateToDetails(id: String, name: String) {
        viewModelScope.launch {
            _sideEffect.emit(CharacterListContract.SideEffect.NavigateToDetails(id, name))
        }
    }

}