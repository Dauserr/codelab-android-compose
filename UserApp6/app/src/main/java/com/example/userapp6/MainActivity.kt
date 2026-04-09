package com.example.userapp6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.userapp6.domain.model.User
import com.example.userapp6.domain.usecase.GetUsersUseCase
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getUsersUseCase: GetUsersUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        // INTENTIONAL LEAK — demonstrates LeakCanary detection
        // A static reference keeps this Activity in memory after it's destroyed.
        // LeakCanary will report this in the notification shade.
        LeakHolder.leakedActivities.add(this)  // ← adds every rotation, never removed

        val users = getUsersUseCase.execute()

        setContent {
            MaterialTheme {
                UserListScreen(users)
            }
        }
    }
}

object LeakHolder {
    val leakedActivities = mutableListOf<MainActivity>()
}

@Composable
fun UserListScreen(users: List<User>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text("Users", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(users) { user ->
            Card(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(user.name, style = MaterialTheme.typography.titleMedium)
                    Text(user.email, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}