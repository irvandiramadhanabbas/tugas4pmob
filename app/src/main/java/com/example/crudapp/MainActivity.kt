package com.example.crudapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.compose.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crudapp.ui.screens.AddUserScreen
import com.example.crudapp.ui.screens.TaskDetailScreen
import com.example.crudapp.ui.screens.UserListScreen
import com.example.crudapp.ui.screens.AddTaskScreen
import com.example.crudapp.ui.theme.CRUDAppTheme
import com.example.crudapp.viewmodels.TaskViewModel
import com.example.crudapp.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {

    private val userViewModel: UserViewModel by viewModels()
    private val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CRUDAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "user_list") {
                    composable("user_list") {
                        UserListScreen(navController)
                    }
                    composable("add_user") {
                        AddUserScreen(navController)
                    }
                    composable("task_detail/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")?.toLong() ?: 0L
                        TaskDetailScreen(userId, navController)
                    }
                    composable("add_task/{userId}") { backStackEntry ->
                        val userId = backStackEntry.arguments?.getString("userId")?.toLong() ?: 0L
                        AddTaskScreen(userId, navController)
                    }
                }
            }
        }
    }
}
