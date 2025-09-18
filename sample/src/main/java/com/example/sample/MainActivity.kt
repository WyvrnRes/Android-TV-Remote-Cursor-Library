package com.example.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tvremotecursor.TVRemoteCursorContainer
import com.example.tvremotecursor.TVRemoteClickable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SampleApp() {
    var lastClickedButton by remember { mutableStateOf("None") }
    
    MaterialTheme {
        TVRemoteCursorContainer(
            cursorColor = Color.Yellow,
            cursorAlpha = 0.8f,
            stepSize = 50f,
            onCursorClick = { position ->
                // This will be called if no clickable area is found at the cursor position
                lastClickedButton = "Background clicked at (${position.x.toInt()}, ${position.y.toInt()})"
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.sample_title),
                    fontSize = 24.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Text(
                    text = stringResource(R.string.sample_description),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(bottom = 32.dp)
                ) {
                    TVRemoteClickable(
                        onClick = { lastClickedButton = "Button 1" }
                    ) {
                        Button(
                            onClick = { /* This will be overridden by TVRemoteClickable */ },
                            modifier = Modifier
                                .height(60.dp)
                                .width(120.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(stringResource(R.string.button_1))
                        }
                    }
                    
                    TVRemoteClickable(
                        onClick = { lastClickedButton = "Button 2" }
                    ) {
                        Button(
                            onClick = { /* This will be overridden by TVRemoteClickable */ },
                            modifier = Modifier
                                .height(60.dp)
                                .width(120.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(stringResource(R.string.button_2))
                        }
                    }
                    
                    TVRemoteClickable(
                        onClick = { lastClickedButton = "Button 3" }
                    ) {
                        Button(
                            onClick = { /* This will be overridden by TVRemoteClickable */ },
                            modifier = Modifier
                                .height(60.dp)
                                .width(120.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Text(stringResource(R.string.button_3))
                        }
                    }
                }
                
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Gray.copy(alpha = 0.2f))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Last Action: $lastClickedButton",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text(
                    text = "Instructions:\n• Use D-pad arrows to move the yellow cursor\n• Press center/enter button to click\n• Try clicking buttons or background",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
            }
        }
    }
}