package com.uvg.navigationapp.presentation.login

sealed interface LoginScreenEvent {
    data class NameChange(val name: String): LoginScreenEvent
    data object SaveName: LoginScreenEvent
}