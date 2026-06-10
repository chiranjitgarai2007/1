package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "levels")
data class LevelEntity(
    @PrimaryKey val id: Int, // 1 to 100
    val chapterId: Int, // 1 to 10
    val question: String,
    val hint: String,
    val correctAnswer: String,
    val explanation: String,
    val isCompleted: Boolean = false,
    val isUnlocked: Boolean = false,
    val starsEarned: Int = 0
)
