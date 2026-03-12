package com.example.userapp6.domain.repository

import com.example.userapp6.domain.model.User

interface UserRepository {
    fun getUsers(): List<User>
}
