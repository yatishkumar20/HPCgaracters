package com.yatish.presentation.ui.character_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yatish.domain.Result
import com.yatish.domain.usecase.GetCharacterByIdUseCase
import com.yatish.presentation.base.ViewIntent
import com.yatish.presentation.constant.Constant.PARAM_CHARACTER_ID
import com.yatish.presentation.di.IoDispatcher
import com.yatish.presentation.mapper.CharacterDetailMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val mapper: CharacterDetailMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel(), CharacterDetailsContract {

    init {
        savedStateHandle.get<String>(PARAM_CHARACTER_ID)?.let {
            sendIntent(CharacterDetailsContract.DetailScreenViewIntent.LoadData(it))
        }
    }

    override fun createInitialState(): CharacterDetailsContract.DetailScreenViewState =
        CharacterDetailsContract.DetailScreenViewState.Loading

    private val _state = MutableStateFlow(createInitialState())
    override val viewState: StateFlow<CharacterDetailsContract.DetailScreenViewState>
        get() = _state.asStateFlow()

    override fun sendIntent(intent: ViewIntent) {
        when (intent) {
            is CharacterDetailsContract.DetailScreenViewIntent.LoadData -> {
                fetchCharacterById(intent.id)
            }
        }
    }

    private fun fetchCharacterById(id: String) {
        viewModelScope.launch(dispatcher) {

            when(val result = getCharacterByIdUseCase(id)) {
                is Result.Success -> {
                    _state.emit(CharacterDetailsContract.DetailScreenViewState.Success(mapper.map(result.data)))
                }
                is Result.Error -> {
                    _state.emit(CharacterDetailsContract.DetailScreenViewState.Error(result.error))
                }
            }
        }
    }
}