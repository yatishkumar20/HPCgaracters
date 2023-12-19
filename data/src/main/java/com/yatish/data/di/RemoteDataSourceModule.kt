package com.yatish.data.di

import com.yatish.data.repository.datasource.remote.HPCharactersRemoteDataSource
import com.yatish.data.repository.datasource.remote.HPCharactersRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    abstract fun bindRemoteHPCharactersDataSource(
        hpCharactersRemoteDataSourceImpl: HPCharactersRemoteDataSourceImpl
    ): HPCharactersRemoteDataSource
}