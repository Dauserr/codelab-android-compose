package com.example.userapp6.data.repository

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class UserRepositoryImplTest {

    private val repository = UserRepositoryImpl()

    @Test
    fun `getUsers returns exactly 3 users`() {
        val users = repository.getUsers()
        assertEquals(3, users.size)
    }

    @Test
    fun `getUsers contains Alice Johnson`() {
        val users = repository.getUsers()
        assertTrue(users.any { it.name == "Alice Johnson" })
    }

    @Test
    fun `getUsers contains Bob Smith`() {
        val users = repository.getUsers()
        assertTrue(users.any { it.name == "Bob Smith" })
    }

    @Test
    fun `all users have non-empty names`() {
        val users = repository.getUsers()
        users.forEach { user ->
            assertTrue("Name should not be blank", user.name.isNotBlank())
        }
    }

    @Test
    fun `all users have valid email format`() {
        val users = repository.getUsers()
        users.forEach { user ->
            assertTrue(
                "Email '${user.email}' is invalid",
                user.email.contains("@") && user.email.contains(".")
            )
        }
    }

    @Test
    fun `all user ids are unique`() {
        val users = repository.getUsers()
        val ids = users.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }
}