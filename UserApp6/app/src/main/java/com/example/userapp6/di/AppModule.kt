package com.example.userapp6.di

import com.example.userapp6.data.repository.NetworkUserRepositoryImpl
import com.example.userapp6.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun bindUserRepository(impl: NetworkUserRepositoryImpl): UserRepository
}