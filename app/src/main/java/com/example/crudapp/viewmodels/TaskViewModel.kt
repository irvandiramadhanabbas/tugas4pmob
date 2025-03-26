package com.example.crudapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crudapp.data.AppDatabase
import com.example.crudapp.data.dao.TaskDao
import com.example.crudapp.data.entities.Task
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {
    private val taskDao: TaskDao = AppDatabase.getInstance(application).taskDao()
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> get() = _tasks

    fun fetchTasksByUserId(userId: Long) {
        viewModelScope.launch {
            _tasks.postValue(taskDao.getTasksByUserId(userId))
        }
    }

    fun addTask(userId: Long, description: String) {
        viewModelScope.launch {
            val newTask = Task(userId = userId, description = description)
            taskDao.insertTask(newTask)
            fetchTasksByUserId(userId)
        }
    }

    fun deleteTask(task: Task, userId: Long) {
        viewModelScope.launch {
            taskDao.deleteTask(task)
            fetchTasksByUserId(userId)
        }
    }
}
