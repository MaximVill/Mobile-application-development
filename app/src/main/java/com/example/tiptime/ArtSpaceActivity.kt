package com.example.tiptime

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tiptime.ui.theme.TipTimeTheme

class ArtSpaceActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            TipTimeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    var currentStep by remember { mutableIntStateOf(1) }
    
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    val imageRes = when (currentStep) {
        1 -> R.drawable.art1
        2 -> R.drawable.art2
        else -> R.drawable.art3
    }
    
    val title = when (currentStep) {
        1 -> "Artwork Title 1"
        2 -> "Artwork Title 2"
        else -> "Artwork Title 3"
    }
    
    val artist = when (currentStep) {
        1 -> "Artist One"
        2 -> "Artist Two"
        else -> "Artist Three"
    }

    val year = when (currentStep) {
        1 -> "2021"
        2 -> "2022"
        else -> "2023"
    }

    if (isLandscape) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .safeDrawingPadding()
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ArtworkDisplay(imageRes = imageRes, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.width(20.dp))
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ArtworkDescriptor(title = title, artist = artist, year = year)
                Spacer(modifier = Modifier.height(20.dp))
                DisplayController(
                    onNextClick = { 
                        if (currentStep < 3) currentStep++ else currentStep = 1 
                    },
                    onPrevClick = { 
                        if (currentStep > 1) currentStep-- else currentStep = 3 
                    }
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .verticalScroll(rememberScrollState())
                .safeDrawingPadding()
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkDisplay(imageRes = imageRes, modifier = Modifier.weight(1f))
            Spacer(modifier = Modifier.height(20.dp))
            ArtworkDescriptor(title = title, artist = artist, year = year)
            Spacer(modifier = Modifier.height(20.dp))
            DisplayController(
                onNextClick = { 
                    if (currentStep < 3) currentStep++ else currentStep = 1 
                },
                onPrevClick = { 
                    if (currentStep > 1) currentStep-- else currentStep = 3 
                }
            )
        }
    }
}

@Composable
fun ArtworkDisplay(imageRes: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 8.dp),
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier.padding(32.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ArtworkDescriptor(title: String, artist: String, year: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFECEBF4))
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Light
        )
        Row {
            Text(
                text = artist,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = " ($year)",
                color = Color.Gray
            )
        }
    }
}

@Composable
fun DisplayController(
    onNextClick: () -> Unit,
    onPrevClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPrevClick,
            modifier = Modifier.width(120.dp)
        ) {
            Text("Previous")
        }
        Button(
            onClick = onNextClick,
            modifier = Modifier.width(120.dp)
        ) {
            Text("Next")
        }
    }
}
