package com.example.mobileapplicationdevelopment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mobileapplicationdevelopment.ui.characterdetail.CharacterDetailScreen
import com.example.mobileapplicationdevelopment.ui.characters.CharactersScreen
import com.example.mobileapplicationdevelopment.ui.theme.MobileApplicationDevelopmentTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MobileApplicationDevelopmentTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RickAndMortyApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun RickAndMortyApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "characters",
        modifier = modifier
    ) {
        composable("characters") {
            CharactersScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("characterDetail/$characterId")
                }
            )
        }
        composable(
            route = "characterDetail/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt("characterId") ?: return@composable
            CharacterDetailScreen(characterId = characterId)
        }
    }
}
