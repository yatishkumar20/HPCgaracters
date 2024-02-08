package com.yatish.presentation.ui.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yatish.domain.Result
import com.yatish.domain.usecase.GetAllCharactersUseCase
import com.yatish.presentation.base.ViewIntent
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

    init {
        sendIntent(CharacterListContract.ListScreenViewIntent.LoadData)
    }
    override fun createInitialState(): CharacterListContract.ListScreenViewState =
        CharacterListContract.ListScreenViewState.Loading

    private val _state = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<CharacterListContract.ListScreenViewState>
        get() = _state.asStateFlow()

    private val _sideEffect = MutableSharedFlow<CharacterListContract.ListScreenSideEffect>()
    override val sideEffect: SharedFlow<CharacterListContract.ListScreenSideEffect>
        get() = _sideEffect.asSharedFlow()

    override fun sendIntent(intent: ViewIntent) {
        when (intent) {
            is CharacterListContract.ListScreenViewIntent.LoadData -> fetchCharacterList()
            is CharacterListContract.ListScreenViewIntent.OnCharacterItemClick -> navigateToDetails(
                intent.id,
                intent.name
            )
        }
    }

    private fun fetchCharacterList() {
        viewModelScope.launch {
            when(val result = getAllCharactersUseCase()) {
                is Result.Success -> {
                    _state.emit(CharacterListContract.ListScreenViewState.Success(result.data.filter { it.house != "" }
                        .map { item -> characterItemMapper.map(item) }))
                }
                is Result.Error -> {
                    _state.emit(CharacterListContract.ListScreenViewState.Error(result.error))
                }
            }
        }
    }

    private fun navigateToDetails(id: String, name: String) {
        viewModelScope.launch {
            _sideEffect.emit(CharacterListContract.ListScreenSideEffect.NavigateToDetails(id, name))
        }
    }

}