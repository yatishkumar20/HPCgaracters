package com.yatish.data.di

import com.yatish.data.repository.HPCharactersRepositoryImpl
import com.yatish.domain.repository.HPCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHPCharactersRepository(
        hpCharactersRepositoryImpl: HPCharactersRepositoryImpl
    ): HPCharactersRepository

}