package com.example.homework3_tbc_it_academy

data class AppUiState(
    val emailValidationText: String? = null,
    val isValidEmail: Boolean = false,

    val usernameValidationText: String? = null,
    val isValidUsername: Boolean = false,

    val firstNameValidationText: String? = null,
    val isValidFirstName: Boolean = false,

    val lastNameValidationText: String? = null,
    val isValidLastName: Boolean = false,

    val ageValidationText: String? = null,
    val isValidAge: Boolean = false
)