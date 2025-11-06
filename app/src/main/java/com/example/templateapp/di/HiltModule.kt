package com.example.templateapp.di

import com.example.templateapp.data.remote.repositories.Repository
import com.example.templateapp.data.remote.repositories.RepositoryInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface HiltModule {
    @Singleton
    @Binds
    fun bindsCitationRepository(
        configRepository: Repository
    ): RepositoryInterface
}

class FakeRepository @Inject constructor() : RepositoryInterface {

    override suspend fun getHelloWorld() {
        TODO("Not yet implemented")
    }
}

