package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface LevelDao {
    @Query("SELECT * FROM levels ORDER BY id ASC")
    fun getAllLevels(): Flow<List<LevelEntity>>

    @Query("SELECT * FROM levels WHERE chapterId = :chapterId ORDER BY id ASC")
    fun getLevelsByChapter(chapterId: Int): Flow<List<LevelEntity>>

    @Query("SELECT * FROM levels WHERE id = :id LIMIT 1")
    fun getLevelById(id: Int): Flow<LevelEntity?>

    @Query("SELECT * FROM levels WHERE isCompleted = 1")
    fun getCompletedLevels(): Flow<List<LevelEntity>>

    @Update
    suspend fun updateLevel(level: LevelEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLevels(levels: List<LevelEntity>)

    @Query("SELECT COUNT(*) FROM levels")
    suspend fun getLevelCount(): Int

    @Query("UPDATE levels SET isCompleted = 0, isUnlocked = CASE WHEN id == 1 THEN 1 ELSE 0 END, starsEarned = 0")
    suspend fun resetProgress()
}
