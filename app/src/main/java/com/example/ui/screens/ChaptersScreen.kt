package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
fun ChaptersScreen(navController: NavController, viewModel: MainViewModel) {
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
                text = "CHAPTERS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = TextPrimary,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {
            items(ChapterData.chapters) { chapter ->
                val progress = viewModel.getChapterProgress(chapter.id)
                val isUnlocked = chapter.id == 1 || viewModel.getChapterProgress(chapter.id - 1).first > 0

                ChapterCard(
                    chapterId = chapter.id,
                    title = chapter.title,
                    description = chapter.description,
                    progress = "${progress.first}/${progress.second}",
                    isUnlocked = isUnlocked,
                    onClick = {
                        if (isUnlocked) navController.navigate("chapter_detail/${chapter.id}")
                    }
                )
            }
        }
    }
}

@Composable
fun ChapterCard(
    chapterId: Int,
    title: String,
    description: String,
    progress: String,
    isUnlocked: Boolean,
    onClick: () -> Unit
) {
    val borderColor = if (isUnlocked) GlowColor.copy(alpha = 0.3f) else Color.White.copy(alpha = 0.03f)
    val bgColor = if (isUnlocked) SurfaceColor else SurfaceColor.copy(alpha = 0.4f)
    val textAlpha = if (isUnlocked) 1f else 0.4f

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(bgColor)
            .border(1.dp, borderColor, RoundedCornerShape(16.dp))
            .then(
                if (isUnlocked) Modifier.clickable { onClick() } else Modifier
            )
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "CH $chapterId",
                        color = if (isUnlocked) GlowColor else TextSecondary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    if (!isUnlocked) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Locked",
                            tint = TextSecondary.copy(alpha = 0.5f),
                            modifier = Modifier.size(12.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = title,
                    color = TextPrimary.copy(alpha = textAlpha),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = description,
                    color = TextSecondary.copy(alpha = textAlpha),
                    fontSize = 13.sp
                )
            }
            
            if (isUnlocked) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(GlowColor.copy(alpha = 0.1f))
                        .border(1.dp, GlowColor.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = progress,
                        color = GlowColor,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
            }
        }
    }
}
