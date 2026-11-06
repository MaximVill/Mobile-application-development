package com.example.mobileapplicationdevelopment

import com.example.mobileapplicationdevelopment.ui.ArtSpaceViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ArtSpaceViewModelTest {

    private lateinit var viewModel: ArtSpaceViewModel

    @Before
    fun setUp() {
        viewModel = ArtSpaceViewModel()
    }

    @Test
    fun artSpaceViewModel_Initialization_FirstArtworkLoaded() {
        val currentState = viewModel.uiState.value
        assertEquals(1, currentState.currentStep)
        assertEquals(R.drawable.ic_art_1, currentState.imageRes)
        assertEquals(R.string.title_1, currentState.titleRes)
    }

    @Test
    fun artSpaceViewModel_NextArtwork_StepIncrementsAndCycles() {
        viewModel.nextArtwork()
        assertEquals(2, viewModel.uiState.value.currentStep)
        assertEquals(R.drawable.ic_art_2, viewModel.uiState.value.imageRes)

        viewModel.nextArtwork()
        assertEquals(3, viewModel.uiState.value.currentStep)
        assertEquals(R.drawable.ic_art_3, viewModel.uiState.value.imageRes)

        viewModel.nextArtwork()
        assertEquals(1, viewModel.uiState.value.currentStep)
        assertEquals(R.drawable.ic_art_1, viewModel.uiState.value.imageRes)
    }

    @Test
    fun artSpaceViewModel_PreviousArtwork_StepDecrementsAndCycles() {
        viewModel.previousArtwork()
        assertEquals(3, viewModel.uiState.value.currentStep)
        assertEquals(R.drawable.ic_art_3, viewModel.uiState.value.imageRes)

        viewModel.previousArtwork()
        assertEquals(2, viewModel.uiState.value.currentStep)
        assertEquals(R.drawable.ic_art_2, viewModel.uiState.value.imageRes)

        viewModel.previousArtwork()
        assertEquals(1, viewModel.uiState.value.currentStep)
        assertEquals(R.drawable.ic_art_1, viewModel.uiState.value.imageRes)
    }
}
