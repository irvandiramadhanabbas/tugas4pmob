package com.example.crudapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks", foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["id"], childColumns = ["userId"], onDelete = ForeignKey.CASCADE)])
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val userId: Long,
    val description: String
)
