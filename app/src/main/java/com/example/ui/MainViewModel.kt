package com.example.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AppDatabase
import com.example.data.ChapterData
import com.example.data.LevelEntity
import com.example.data.LevelGenerator
import com.example.data.SettingsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val db: AppDatabase,
    private val settingsRepository: SettingsRepository
) : ViewModel() {

    private val levelDao = db.levelDao()

    private val _levels = MutableStateFlow<List<LevelEntity>>(emptyList())
    val levels: StateFlow<List<LevelEntity>> = _levels.asStateFlow()

    private val _currentLevel = MutableStateFlow<LevelEntity?>(null)
    val currentLevel: StateFlow<LevelEntity?> = _currentLevel.asStateFlow()

    val soundOn = settingsRepository.soundOnFlow
    val vibrationOn = settingsRepository.vibrationOnFlow

    init {
        viewModelScope.launch {
            if (levelDao.getLevelCount() == 0) {
                levelDao.insertLevels(LevelGenerator.generateLevels())
            }
            levelDao.getAllLevels().collect { lst ->
                _levels.value = lst
            }
        }
    }

    fun getUnlockedLevelsForChapter(chapterId: Int): List<LevelEntity> {
        return _levels.value.filter { it.chapterId == chapterId }
    }
    
    fun getChapterProgress(chapterId: Int): Pair<Int, Int> {
        val chapterLevels = _levels.value.filter { it.chapterId == chapterId }
        val completed = chapterLevels.count { it.isCompleted }
        return Pair(completed, chapterLevels.size)
    }

    fun loadLevel(id: Int) {
        viewModelScope.launch {
            _currentLevel.value = _levels.value.find { it.id == id }
        }
    }

    fun checkAnswer(answer: String): Boolean {
        val level = _currentLevel.value ?: return false
        if (level.correctAnswer == answer) {
            val updatedLevel = level.copy(isCompleted = true, starsEarned = 3) // simplistic 3 star
            
            // unlock next level
            val allLevels = _levels.value
            val nextLevel = allLevels.find { it.id == level.id + 1 }
            
            viewModelScope.launch {
                levelDao.updateLevel(updatedLevel)
                if (nextLevel != null) {
                    levelDao.updateLevel(nextLevel.copy(isUnlocked = true))
                }
            }
            return true
        }
        return false
    }

    fun toggleSound(on: Boolean) {
        viewModelScope.launch { settingsRepository.setSoundOn(on) }
    }

    fun toggleVibration(on: Boolean) {
        viewModelScope.launch { settingsRepository.setVibrationOn(on) }
    }

    fun resetProgress() {
        viewModelScope.launch {
            levelDao.resetProgress()
        }
    }

    fun toggleTheme(dark: Boolean) {
        viewModelScope.launch { settingsRepository.setDarkMode(dark) }
    }
}
