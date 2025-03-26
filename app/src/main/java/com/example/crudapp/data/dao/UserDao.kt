package com.example.crudapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.crudapp.data.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>
}
