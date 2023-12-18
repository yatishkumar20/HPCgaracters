package com.yatish.domain.di

import com.yatish.domain.usecase.GetAllCharactersUseCase
import com.yatish.domain.usecase.GetAllCharactersUseCaseImpl
import com.yatish.domain.usecase.GetCharacterByIdUseCase
import com.yatish.domain.usecase.GetCharacterByIdUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetAllCharactersUseCase(
        getAllCharactersUseCaseImpl: GetAllCharactersUseCaseImpl
    ): GetAllCharactersUseCase

    @Binds
    abstract fun bindGetCharacterByIdUseCase(
        getCharacterByIdUseCaseImpl: GetCharacterByIdUseCaseImpl
    ): GetCharacterByIdUseCase

}