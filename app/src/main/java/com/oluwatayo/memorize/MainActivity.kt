package com.oluwatayo.memorize

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.oluwatayo.memorize.ui.theme.MemorizeTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.clip

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemorizeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Memorize()
                }
            }
        }
    }
}

@Composable
fun Memorize() {
    val emojis = listOf(
        "✈️",
        "🚐",
        "🚀",
        "🚌",
        "🥁",
        "🧼",
        "🪬",
        "💊",
        "⚰️",
        "⚱️",
        "💉",
        "💡",
        "📷",
        "📱",
        "🕰️",
        "🐶",
        "🦀",
        "🥉",
        "🎧"
    )
    var endIndex: Int by remember {
        mutableStateOf(emojis.size)
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LazyVerticalGrid(columns = GridCells.Adaptive(80.dp), modifier = Modifier
            .weight(1f),
            content = {
                items(emojis.subList(0, endIndex).size) { index ->
                    MemorizeCard(text = emojis[index])
                }
            })
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                Icons.Filled.Delete,
                contentDescription = "Delete",
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(40.dp)
                    .clickable {
                        if (endIndex > 1)
                            endIndex--
                    }
            )
            Icon(
                Icons.Filled.Add, contentDescription = "Add",
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        if (endIndex < emojis.size)
                            endIndex++
                    }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemorizeCard(text: String) {
    var isFaceUp: Boolean by remember {
        mutableStateOf(false)
    }
    Box(contentAlignment = Alignment.Center) {
        val roundedCornerShape = RoundedCornerShape(corner = CornerSize(20.dp))
        Card(
            shape = roundedCornerShape,
            modifier = Modifier
                .aspectRatio(
                    "0.667".toFloat()
                )
                .padding(horizontal = 6.dp, vertical = 6.dp),
            colors = CardDefaults.cardColors(
                containerColor = if (isFaceUp) Color.White else Color.Red,
                contentColor = Color.White
            ),
            border = BorderStroke(3.dp, Color.Red),
            onClick = {
                isFaceUp = isFaceUp.not()
            }
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (isFaceUp)
                    Text(
                        text = text,
                        style = MaterialTheme.typography.displaySmall
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemorizeTheme {
        Memorize()
    }
}