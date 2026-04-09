package com.example.userapp6.data.repository

import com.example.userapp6.data.remote.UserApiService
import com.example.userapp6.domain.model.User
import com.example.userapp6.domain.repository.UserRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NetworkUserRepositoryImpl @Inject constructor(
    private val apiService: UserApiService
) : UserRepository {
    override fun getUsers(): List<User> = runBlocking {
        apiService.getUsers().map { dto ->
            User(dto.id, dto.name, dto.email)
        }
    }
}