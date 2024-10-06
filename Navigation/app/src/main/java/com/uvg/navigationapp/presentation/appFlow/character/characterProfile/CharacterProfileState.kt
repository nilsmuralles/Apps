package com.uvg.navigationapp.presentation.appFlow.character.characterProfile

import com.uvg.navigationapp.data.model.Character

data class CharacterProfileState(
    val isLoading: Boolean = false,
    val data: Character? = null,
    val hasError: Boolean = false
)
