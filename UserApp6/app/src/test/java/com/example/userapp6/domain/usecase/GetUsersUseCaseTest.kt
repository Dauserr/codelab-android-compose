package com.example.userapp6.domain.usecase

import com.example.userapp6.domain.model.User
import com.example.userapp6.domain.repository.UserRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetUsersUseCaseTest {

    private val mockRepository: UserRepository = mock()
    private val useCase = GetUsersUseCase(mockRepository)

    @Test
    fun `execute returns list from repository`() {
        val expectedUsers = listOf(
            User(1, "Alice Johnson", "alice@example.com"),
            User(2, "Bob Smith", "bob@example.com")
        )
        whenever(mockRepository.getUsers()).thenReturn(expectedUsers)

        val result = useCase.execute()

        assertEquals(expectedUsers, result)
    }

    @Test
    fun `execute calls repository exactly once`() {
        whenever(mockRepository.getUsers()).thenReturn(emptyList())

        useCase.execute()

        verify(mockRepository).getUsers()
    }

    @Test
    fun `execute returns empty list when repository is empty`() {
        whenever(mockRepository.getUsers()).thenReturn(emptyList())

        val result = useCase.execute()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `execute returns correct user count`() {
        val users = listOf(
            User(1, "Alice", "a@a.com"),
            User(2, "Bob", "b@b.com"),
            User(3, "Carol", "c@c.com")
        )
        whenever(mockRepository.getUsers()).thenReturn(users)

        val result = useCase.execute()

        assertEquals(3, result.size)
    }
}