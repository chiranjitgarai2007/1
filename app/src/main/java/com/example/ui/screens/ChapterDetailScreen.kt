package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.data.ChapterData
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun ChapterDetailScreen(navController: NavController, viewModel: MainViewModel, chapterId: Int) {
    val chapter = ChapterData.chapters.find { it.id == chapterId } ?: return
    val levels = viewModel.getUnlockedLevelsForChapter(chapterId)
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(BlackBackground, DarkGrayBackground)))
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 20.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        
        // Navigation Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.03f))
                    .border(1.dp, Color.White.copy(alpha = 0.08f), CircleShape)
                    .clickable { navController.popBackStack() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = TextPrimary,
                    modifier = Modifier.size(18.dp)
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "CHAPTER $chapterId",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = TextPrimary,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        Text(
            text = chapter.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = TextPrimary,
            letterSpacing = 1.sp
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = chapter.description,
            color = TextSecondary,
            fontSize = 14.sp
        )
        
        Spacer(modifier = Modifier.height(28.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(4),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            items(levels) { level ->
                LevelNode(
                    levelNumber = level.id,
                    isUnlocked = level.isUnlocked,
                    isCompleted = level.isCompleted
                ) {
                    if (level.isUnlocked) {
                        navController.navigate("level/${level.id}")
                    }
                }
            }
        }
    }
}

@Composable
fun LevelNode(levelNumber: Int, isUnlocked: Boolean, isCompleted: Boolean, onClick: () -> Unit) {
    val borderColor = if (isCompleted) SuccessColor.copy(alpha = 0.8f) else if (isUnlocked) GlowColor.copy(alpha = 0.5f) else Color.White.copy(alpha = 0.03f)
    val bgColor = if (isCompleted) SuccessColor.copy(alpha = 0.06f) else if (isUnlocked) SurfaceColor else SurfaceColor.copy(alpha = 0.3f)
    
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .clickable(enabled = isUnlocked) { onClick() },
        contentAlignment = Alignment.Center
    ) {
        if (isUnlocked) {
            Text(
                text = levelNumber.toString(),
                color = if (isCompleted) SuccessColor else TextPrimary,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 16.sp
            )
        } else {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Locked",
                tint = TextSecondary.copy(alpha = 0.4f),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}
