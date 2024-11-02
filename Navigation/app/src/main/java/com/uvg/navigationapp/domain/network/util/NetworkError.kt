package com.uvg.navigationapp.domain.network.util

interface Error

enum class DataError: Error {
    NO_INTERNET,
    GENERIC_ERROR
}