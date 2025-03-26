package com.example.crudapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.crudapp.data.AppDatabase
import com.example.crudapp.data.dao.UserDao
import com.example.crudapp.data.entities.User
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val userDao: UserDao = AppDatabase.getInstance(application).userDao()
    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(userDao.getAllUsers())
        }
    }

    fun addUser(name: String) {
        viewModelScope.launch {
            val newUser = User(name = name)
            userDao.insertUser(newUser)
            fetchUsers()
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userDao.deleteUser(user)
            fetchUsers()
        }
    }
}
