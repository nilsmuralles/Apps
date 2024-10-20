package com.uvg.navigationapp.presentation.appFlow.character.characterList

import com.uvg.navigationapp.domain.model.Character

data class CharacterLIstState(
    val isLoading: Boolean = false,
    val data: List<Character>? = null,
    val hasError: Boolean = false
)
