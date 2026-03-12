package com.example.userapp6.data.repository

import com.example.userapp6.domain.model.User
import com.example.userapp6.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun getUsers() = listOf(
        User(1, "Alice Johnson", "alice@example.com"),
        User(2, "Bob Smith", "bob@example.com"),
        User(3, "Carol White", "carol@example.com")
    )
}