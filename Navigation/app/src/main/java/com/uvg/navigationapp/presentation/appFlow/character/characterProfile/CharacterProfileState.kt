package com.uvg.navigationapp.presentation.appFlow.character.characterProfile

import com.uvg.navigationapp.domain.model.Character

data class CharacterProfileState(
    val isLoading: Boolean = false,
    val data: Character? = null,
    val hasError: Boolean = false
)
