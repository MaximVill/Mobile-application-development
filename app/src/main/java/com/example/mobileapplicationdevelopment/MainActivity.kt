package com.example.mobileapplicationdevelopment

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mobileapplicationdevelopment.ui.ArtSpaceUiState
import com.example.mobileapplicationdevelopment.ui.ArtSpaceViewModel
import com.example.mobileapplicationdevelopment.ui.theme.MobileApplicationDevelopmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileApplicationDevelopmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ArtSpaceApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Точка входа приложения Art Space, связывающая ViewModel и UI с помощью паттерна UDF.
 */
@Composable
fun ArtSpaceApp(
    modifier: Modifier = Modifier,
    viewModel: ArtSpaceViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    ArtSpaceScreen(
        uiState = uiState,
        onPreviousClick = { viewModel.previousArtwork() },
        onNextClick = { viewModel.nextArtwork() },
        modifier = modifier
    )
}

/**
 * Stateless Composable-компонент экрана Art Space.
 */
@Composable
fun ArtSpaceScreen(
    uiState: ArtSpaceUiState,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                ArtworkDisplay(uiState.imageRes)
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ArtworkDescriptor(
                    titleRes = uiState.titleRes,
                    artistRes = uiState.artistRes,
                    yearRes = uiState.yearRes
                )
                Spacer(modifier = Modifier.height(24.dp))
                DisplayController(
                    onPreviousClick = onPreviousClick,
                    onNextClick = onNextClick
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
            ArtworkDisplay(uiState.imageRes)
            Spacer(modifier = Modifier.height(32.dp))
            ArtworkDescriptor(
                titleRes = uiState.titleRes,
                artistRes = uiState.artistRes,
                yearRes = uiState.yearRes
            )
            Spacer(modifier = Modifier.height(32.dp))
            DisplayController(
                onPreviousClick = onPreviousClick,
                onNextClick = onNextClick
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
        ArtSpaceScreen(
            uiState = ArtSpaceUiState(),
            onPreviousClick = {},
            onNextClick = {}
        )
    }
}
