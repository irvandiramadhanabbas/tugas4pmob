package com.example.crudapp.ui.screens

import android.os.Bundle
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.crudapp.viewmodels.TaskViewModel

@Composable
fun TaskDetailScreen(userId: Long, navController: NavController) {
    val taskViewModel: TaskViewModel = viewModel()
    val tasks by taskViewModel.tasks.observeAsState(emptyList())

    LaunchedEffect(userId) {
        taskViewModel.fetchTasksByUserId(userId)
    }

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tasks) { task ->
            ListItem(
                headlineContent = { Text(task.description) },
                modifier = Modifier.padding(16.dp)
            )
            Button(
                onClick = {
                    taskViewModel.deleteTask(task, userId)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text("Delete Task")
            }
        }
    }

    Button(
        onClick = {
            navController.navigate("add_task/$userId")
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("Add New Task")
    }
}
