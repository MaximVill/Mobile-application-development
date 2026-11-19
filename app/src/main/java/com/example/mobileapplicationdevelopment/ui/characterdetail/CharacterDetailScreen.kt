package com.example.mobileapplicationdevelopment.ui.characterdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CharacterDetailScreen(
    characterId: Int,
    viewModel: CharacterDetailViewModel = koinViewModel { parametersOf(characterId) }
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = uiState) {
            is CharacterDetailUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is CharacterDetailUiState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Error: ${state.message}", color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadCharacter() }) {
                        Text("Retry")
                    }
                }
            }
            is CharacterDetailUiState.Success -> {
                val character = state.character
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    AsyncImage(
                        model = character.image,
                        contentDescription = character.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = character.name, style = MaterialTheme.typography.headlineLarge)
                    Text(text = "Status: ${character.status}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Species: ${character.species}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Gender: ${character.gender}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Origin: ${character.origin.name}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Location: ${character.location.name}", style = MaterialTheme.typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "Episodes:", style = MaterialTheme.typography.titleLarge)
                    character.episode.forEach { episodeUrl ->
                        // Just extract episode number for display
                        val episodeNumber = episodeUrl.substringAfterLast("/")
                        Text(text = "Episode $episodeNumber", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
