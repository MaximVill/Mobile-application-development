package com.example.mobileapplicationdevelopment

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mobileapplicationdevelopment.ui.theme.MobileApplicationDevelopmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileApplicationDevelopmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(modifier: Modifier = Modifier) {
    var currentStep by remember { mutableIntStateOf(1) }

    val imageRes = when (currentStep) {
        1 -> R.drawable.ic_art_1
        2 -> R.drawable.ic_art_2
        else -> R.drawable.ic_art_3
    }

    val titleRes = when (currentStep) {
        1 -> R.string.title_1
        2 -> R.string.title_2
        else -> R.string.title_3
    }

    val artistRes = when (currentStep) {
        1 -> R.string.artist_1
        2 -> R.string.artist_2
        else -> R.string.artist_3
    }

    val yearRes = when (currentStep) {
        1 -> R.string.year_1
        2 -> R.string.year_2
        else -> R.string.year_3
    }

    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    if (isLandscape) {
        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(16.dp)
            ) {
                ArtworkDisplay(imageRes)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ArtworkDescriptor(titleRes, artistRes, yearRes)
                Spacer(modifier = Modifier.height(24.dp))
                DisplayController(
                    onPreviousClick = {
                        if (currentStep > 1) currentStep-- else currentStep = 3
                    },
                    onNextClick = {
                        if (currentStep < 3) currentStep++ else currentStep = 1
                    }
                )
            }
        }
    } else {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ArtworkDisplay(imageRes)
            Spacer(modifier = Modifier.height(32.dp))
            ArtworkDescriptor(titleRes, artistRes, yearRes)
            Spacer(modifier = Modifier.height(32.dp))
            DisplayController(
                onPreviousClick = {
                    if (currentStep > 1) currentStep-- else currentStep = 3
                },
                onNextClick = {
                    if (currentStep < 3) currentStep++ else currentStep = 1
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
            .wrapContentHeight(),
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .padding(32.dp)
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun ArtworkDescriptor(titleRes: Int, artistRes: Int, yearRes: Int, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = titleRes),
                fontSize = 24.sp,
                fontWeight = FontWeight.Light,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Row {
                Text(
                    text = stringResource(id = artistRes),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = " (${stringResource(id = yearRes)})",
                    fontWeight = FontWeight.Light,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun DisplayController(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(id = R.string.previous))
        }
        Spacer(modifier = Modifier.width(16.dp))
        Button(
            onClick = onNextClick,
            modifier = Modifier.weight(1f)
        ) {
            Text(text = stringResource(id = R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    MobileApplicationDevelopmentTheme {
        ArtSpaceScreen()
    }
}
