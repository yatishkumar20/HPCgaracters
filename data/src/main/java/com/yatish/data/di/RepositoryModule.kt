package com.yatish.data.di

import com.yatish.data.repository.HPCharactersRepositoryImpl
import com.yatish.data.repository.datasource.remote.HPCharactersRemoteDataSource
import com.yatish.data.repository.datasource.remote.HPCharactersRemoteDataSourceImpl
import com.yatish.domain.repository.HPCharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindHPCharactersRepository(
        hpCharactersRepositoryImpl: HPCharactersRepositoryImpl
    ): HPCharactersRepository

    @Binds
    abstract fun bindRemoteHPCharactersDataSource(
        hpCharactersRemoteDataSourceImpl: HPCharactersRemoteDataSourceImpl
    ): HPCharactersRemoteDataSource
}