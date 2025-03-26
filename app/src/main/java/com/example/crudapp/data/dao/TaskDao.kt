package com.example.crudapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.crudapp.data.entities.Task

@Dao
interface TaskDao {
    @Insert
    suspend fun insertTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("SELECT * FROM tasks WHERE userId = :userId")
    suspend fun getTasksByUserId(userId: Long): List<Task>
}
