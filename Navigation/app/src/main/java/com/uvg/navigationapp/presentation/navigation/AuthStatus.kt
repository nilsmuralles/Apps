package com.uvg.navigationapp.presentation.navigation

sealed interface AuthStatus {
    data object Authenticated: AuthStatus
    data object NonAuthenticated: AuthStatus
    data object Loading: AuthStatus
}