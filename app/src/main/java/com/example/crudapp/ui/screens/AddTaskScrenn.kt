package com.example.crudapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.crudapp.viewmodels.TaskViewModel

@Composable
fun AddTaskScreen(userId: Long, navController: NavController) {
    val taskViewModel: TaskViewModel = viewModel()
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Enter Task Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (description.isNotBlank()) {
                    taskViewModel.addTask(userId, description)
                    navController.popBackStack()  // Kembali ke detail task
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Task")
        }
    }
}
