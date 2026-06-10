package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import com.example.ui.MainViewModel
import com.example.ui.theme.*

@Composable
fun SettingsScreen(navController: NavController, viewModel: MainViewModel) {
    val soundOn by viewModel.soundOn.collectAsState(initial = true)
    val vibrationOn by viewModel.vibrationOn.collectAsState(initial = true)
    
    var showResetDialog by remember { mutableStateOf(false) }

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
                text = "SETTINGS",
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = TextPrimary,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(SurfaceColor)
                .border(1.dp, Color.White.copy(alpha = 0.04f), RoundedCornerShape(20.dp))
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SettingRow(title = "Sound Effects", description = "Feedback sound on keys", isChecked = soundOn) {
                viewModel.toggleSound(it)
            }
            Divider(color = Color.White.copy(alpha = 0.04f), thickness = 1.dp)
            SettingRow(title = "Haptic Vibration", description = "Vibrate on click states", isChecked = vibrationOn) {
                viewModel.toggleVibration(it)
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        // Custom Styled Reset Button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(ErrorColor.copy(alpha = 0.08f))
                .border(1.dp, ErrorColor.copy(alpha = 0.3f), RoundedCornerShape(16.dp))
                .clickable { showResetDialog = true },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "RESET APP PROGRESS",
                color = ErrorColor,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }

    if (showResetDialog) {
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { 
                Text(
                    "Reset All Progress?", 
                    fontWeight = FontWeight.Bold, 
                    fontSize = 20.sp,
                    color = TextPrimary
                ) 
            },
            text = { 
                Text(
                    "Are you sure you want to reset all unlocked levels and achievements back to Level 1? This action cannot be reversed.",
                    color = TextSecondary,
                    fontSize = 14.sp
                ) 
            },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.resetProgress()
                    showResetDialog = false
                    navController.popBackStack()
                }) {
                    Text("RESET PROGRESS", color = ErrorColor, fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("CANCEL", color = TextPrimary, fontWeight = FontWeight.Bold)
                }
            },
            containerColor = SurfaceColor,
            titleContentColor = TextPrimary,
            textContentColor = TextSecondary,
            shape = RoundedCornerShape(24.dp)
        )
    }
}

@Composable
fun SettingRow(title: String, description: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f).padding(end = 16.dp)) {
            Text(
                text = title,
                color = TextPrimary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = description,
                color = TextSecondary,
                fontSize = 12.sp
            )
        }
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = SwitchDefaults.colors(
                checkedThumbColor = GlowColor,
                checkedTrackColor = GlowColor.copy(alpha = 0.3f),
                uncheckedThumbColor = TextSecondary.copy(alpha = 0.8f),
                uncheckedTrackColor = Color.White.copy(alpha = 0.05f),
                uncheckedBorderColor = Color.White.copy(alpha = 0.1f)
            )
        )
    }
}
