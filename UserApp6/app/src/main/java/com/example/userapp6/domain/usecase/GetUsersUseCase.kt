package com.example.userapp6.domain.usecase

import com.example.userapp6.domain.model.User
import com.example.userapp6.domain.repository.UserRepository
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: UserRepository
) {
    fun execute(): List<User> = repository.getUsers()
}
