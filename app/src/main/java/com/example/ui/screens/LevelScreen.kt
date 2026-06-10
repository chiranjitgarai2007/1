package com.example.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.ui.MainViewModel
import com.example.ui.components.CustomKeypad
import com.example.ui.theme.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LevelScreen(navController: NavController, viewModel: MainViewModel, levelId: Int) {
    val level by viewModel.currentLevel.collectAsState()
    var input by remember { mutableStateOf("") }
    var showHint by remember { mutableStateOf(false) }
    var answerState by remember { mutableStateOf(0) } // 0=normal, 1=correct, -1=wrong
    val coroutineScope = rememberCoroutineScope()

    val borderColor by animateColorAsState(
        targetValue = when (answerState) {
            1 -> SuccessColor
            -1 -> ErrorColor
            else -> Color.White.copy(alpha = 0.08f)
        },
        animationSpec = tween(300),
        label = "borderColor"
    )

    LaunchedEffect(levelId) {
        viewModel.loadLevel(levelId)
    }

    if (level == null) return

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(BlackBackground, DarkGrayBackground)))
            .windowInsetsPadding(WindowInsets.systemBars)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Navigation Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
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
                    text = "LEVEL ${level?.id}",
                    color = GlowColor,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 1.5.sp
                )
            }
            
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.03f))
                    .border(1.dp, Color.White.copy(alpha = 0.08f), CircleShape)
                    .clickable { showHint = !showHint },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Lightbulb,
                    contentDescription = "Hint",
                    tint = if (showHint) HintColor else TextPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Question Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .background(SurfaceColor)
                .border(1.dp, borderColor, RoundedCornerShape(24.dp))
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = level?.question ?: "",
                fontSize = 26.sp,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                lineHeight = 36.sp
            )
        }

        if (showHint) {
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(HintColor.copy(alpha = 0.08f))
                    .border(1.dp, HintColor.copy(alpha = 0.2f), RoundedCornerShape(12.dp))
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "Hint: ${level?.hint}",
                    color = HintColor,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        
        Spacer(modifier = Modifier.height(36.dp))
        
        // Input display screen value with absolute focus display
        Box(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(64.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.White.copy(alpha = 0.02f))
                .border(1.dp, Color.White.copy(alpha = 0.04f), RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = input.ifEmpty { "TAP KEYPAD" },
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (input.isEmpty()) TextSecondary.copy(alpha = 0.3f) else TextPrimary,
                letterSpacing = 2.sp
            )
        }
        
        Spacer(modifier = Modifier.weight(1f))
        
        CustomKeypad(
            modifier = Modifier.padding(bottom = 12.dp),
            onNumber = { num ->
                if (answerState == 0) input += num
            },
            onDelete = {
                if (answerState == 0 && input.isNotEmpty()) {
                    input = input.dropLast(1)
                }
            },
            onEnter = {
                if (input.isNotEmpty() && answerState == 0) {
                    val correct = viewModel.checkAnswer(input)
                    if (correct) {
                        answerState = 1
                        coroutineScope.launch {
                            delay(800)
                            // navigate to next level
                            navController.navigate("level/${levelId + 1}") {
                                popUpTo("chapter_detail/${level!!.chapterId}")
                            }
                        }
                    } else {
                        answerState = -1
                        coroutineScope.launch {
                            delay(500)
                            input = ""
                            answerState = 0
                        }
                    }
                }
            }
        )
    }
}
