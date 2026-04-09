package com.example.userapp6.data.remote

import retrofit2.http.GET

interface UserApiService {
    @GET("users")
    suspend fun getUsers(): List<UserDto>
}