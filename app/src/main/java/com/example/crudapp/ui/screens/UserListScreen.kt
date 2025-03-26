package com.example.crudapp.ui.screens

import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crudapp.viewmodels.UserViewModel

@Composable
fun UserListScreen(navController: NavController) {
    val userViewModel: UserViewModel = viewModel()
    val users by userViewModel.users.observeAsState(emptyList())

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(users) { user ->
            ListItem(
                headlineContent = { Text(user.name) },
                modifier = Modifier
                    .clickable {
                        navController.navigate("task_detail/${user.id}")
                    }
                    .padding(16.dp)
            )
        }
    }

    Button(
        onClick = {
            navController.navigate("add_user")
        },
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text("Add New User")
    }
}
