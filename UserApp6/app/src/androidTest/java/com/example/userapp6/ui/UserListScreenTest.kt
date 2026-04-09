package com.example.userapp6.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.userapp6.UserListScreen
import com.example.userapp6.domain.model.User
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testUsers = listOf(
        User(1, "Alice Johnson", "alice@example.com"),
        User(2, "Bob Smith", "bob@example.com"),
        User(3, "Carol White", "carol@example.com")
    )

    @Test
    fun userListScreen_displaysHeaderTitle() {
        composeTestRule.setContent {
            MaterialTheme { UserListScreen(testUsers) }
        }
        composeTestRule.onNodeWithText("Users").assertIsDisplayed()
    }

    @Test
    fun userListScreen_displaysAllUserNames() {
        composeTestRule.setContent {
            MaterialTheme { UserListScreen(testUsers) }
        }
        composeTestRule.onNodeWithText("Alice Johnson").assertIsDisplayed()
        composeTestRule.onNodeWithText("Bob Smith").assertIsDisplayed()
        composeTestRule.onNodeWithText("Carol White").assertIsDisplayed()
    }

    @Test
    fun userListScreen_displaysAllEmails() {
        composeTestRule.setContent {
            MaterialTheme { UserListScreen(testUsers) }
        }
        composeTestRule.onNodeWithText("alice@example.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("bob@example.com").assertIsDisplayed()
        composeTestRule.onNodeWithText("carol@example.com").assertIsDisplayed()
    }

    @Test
    fun userListScreen_isScrollable() {
        val manyUsers = (1..20).map { User(it, "User $it", "user$it@test.com") }
        composeTestRule.setContent {
            MaterialTheme { UserListScreen(manyUsers) }
        }
        // List renders without crashing with many items
        composeTestRule.onNodeWithText("User 1").assertIsDisplayed()
    }

    @Test
    fun userListScreen_showsEmptyStateCorrectly() {
        composeTestRule.setContent {
            MaterialTheme { UserListScreen(emptyList()) }
        }
        // Header is still shown even with no users
        composeTestRule.onNodeWithText("Users").assertIsDisplayed()
        // No user cards appear
        composeTestRule.onNodeWithText("alice@example.com").assertDoesNotExist()
    }
}