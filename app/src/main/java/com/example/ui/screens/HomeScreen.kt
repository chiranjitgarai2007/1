package com.example.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.AlternateEmail
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun HomeScreen(navController: NavController, viewModel: MainViewModel) {
    val levels by viewModel.levels.collectAsState()
    val currentUncompleted = levels.firstOrNull { !it.isCompleted && it.isUnlocked }
    val currentLevelId = currentUncompleted?.id ?: 1

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(BlackBackground, DarkGrayBackground)
                )
            )
            .windowInsetsPadding(WindowInsets.systemBars),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                // Sigma Logo Box
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(GlowColor)
                        .glow(GlowColor, 16.dp, 0.4f)
                        .border(1.dp, Color.White.copy(alpha = 0.3f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Σ",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Italic,
                        color = Color.Black
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "MATH",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Black,
                    color = TextPrimary,
                    letterSpacing = 8.sp
                )
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = "Learn • Practice • Improve",
                    fontSize = 11.sp,
                    color = TextSecondary,
                    letterSpacing = 4.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            // Main Buttons Section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Primary CONTINUE Button (White background, black text)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(Color.White)
                        .clickable {
                            if (currentUncompleted != null) {
                                navController.navigate("level/${currentUncompleted.id}")
                            } else if (levels.isNotEmpty() && levels.last().isCompleted) {
                                navController.navigate("level/${levels.last().id}")
                            }
                        }
                        .padding(horizontal = 24.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "CONTINUE",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            letterSpacing = 1.5.sp
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color.Black.copy(alpha = 0.1f))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "LVL $currentLevelId",
                                color = Color.Black,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // START LEARNING
                ElegantButton(
                    text = "START LEARNING",
                    borderAlpha = 0.15f
                ) {
                    navController.navigate("chapters")
                }

                // Side by Side Practice & Challenge
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Box(modifier = Modifier.weight(1f)) {
                        ElegantButton(text = "PRACTICE", borderAlpha = 0.08f) {
                            navController.navigate("chapters")
                        }
                    }
                    Box(modifier = Modifier.weight(1f)) {
                        Box(modifier = Modifier.fillMaxWidth()) {
                            ElegantButton(text = "CHALLENGE", enabled = false, borderAlpha = 0.08f)
                            // Elegant glowing notification dot
                            Box(
                                modifier = Modifier
                                    .align(Alignment.TopEnd)
                                    .padding(top = 8.dp, end = 8.dp)
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(GlowColor)
                                    .glow(GlowColor, 4.dp, 0.6f)
                            )
                        }
                    }
                }

                // SETTINGS
                ElegantButton(
                    text = "SETTINGS",
                    borderAlpha = 0.08f
                ) {
                    navController.navigate("settings")
                }
            }

            // Social Footer Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(
                    text = "FOLLOW US",
                    color = TextSecondary,
                    fontSize = 10.sp,
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    SocialIcon(Icons.Outlined.CameraAlt) // Instagram
                    SocialIcon(Icons.Outlined.AlternateEmail) // X/Twitter
                    SocialIcon(Icons.Outlined.MusicNote) // TikTok
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Android Nav Pill Visual
                Box(
                    modifier = Modifier
                        .width(112.dp)
                        .height(4.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.15f))
                )
            }
        }
    }
}

@Composable
fun ElegantButton(
    text: String,
    enabled: Boolean = true,
    borderAlpha: Float = 0.08f,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(if (enabled) SurfaceColor else SurfaceColor.copy(alpha = 0.5f))
            .border(
                1.dp,
                if (enabled) Color.White.copy(alpha = borderAlpha) else Color.White.copy(alpha = 0.03f),
                RoundedCornerShape(16.dp)
            )
            .then(
                if (enabled) Modifier.clickable { onClick() } else Modifier
            ),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = if (enabled) text else "$text (COMING SOON)",
            color = if (enabled) TextPrimary else TextSecondary.copy(alpha = 0.5f),
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(horizontal = 24.dp),
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun SocialIcon(icon: ImageVector) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.03f))
            .border(1.dp, Color.White.copy(alpha = 0.08f), CircleShape)
            .clickable { /* Follow implementation potential link */ },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(18.dp)
        )
    }
}
