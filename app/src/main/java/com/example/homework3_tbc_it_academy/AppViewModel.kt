package com.example.homework3_tbc_it_academy

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

// Use of the AndroidViewModel supports the access to application resources.
class AppViewModel(application: Application): AndroidViewModel(application) {
    private val _uiState = MutableLiveData<AppUiState>()
    val uiState: LiveData<AppUiState> = _uiState

    private val emailHelperText = application.getString(R.string.email_helper_text)
    private val usernameHelperText = application.getString(R.string.username_helper_text)
    private val firstNameHelperText = application.getString(R.string.first_name_helper_text)
    private val lastNameHelperText = application.getString(R.string.last_name_helper_text)
    private val ageHelperText = application.getString(R.string.age_helper_text)

    // App is launched with empty values.
    init {
        _uiState.value = AppUiState()
    }

    fun validateEmail(email: String) {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotBlank()
        _uiState.value =
            _uiState.value?.copy(
                emailValidationText = if (isValidEmail) null else emailHelperText,
                isValidEmail = isValidEmail
            )
    }

    fun validateUsername(username: String) {
        val isValidUsername =
            username.isNotBlank() && username.length >= MIN_USERNAME_LENGTH && !username.contains(EMPTY_SPACE)
        _uiState.value =
            _uiState.value?.copy(
                usernameValidationText = if (isValidUsername) null else usernameHelperText,
                isValidUsername = isValidUsername
            )
    }

    fun validateFirstName(firstName: String) {
        val isValidFirstName = firstName.isNotBlank() && firstName.matches(Regex(NAME_PATTERN))
        _uiState.value =
            _uiState.value?.copy(
                firstNameValidationText = if (isValidFirstName) null else firstNameHelperText,
                isValidFirstName = isValidFirstName
            )
    }

    fun validateLastName(lastName: String) {
        val isValidLastName = lastName.isNotBlank() && lastName.matches(Regex(NAME_PATTERN))
        _uiState.value =
            _uiState.value?.copy(
                lastNameValidationText = if (isValidLastName) null else lastNameHelperText,
                isValidLastName = isValidLastName
            )
    }

    fun validateAge(age: String) {
        val ageToInt = age.toIntOrNull()
        val isValidAge = when {
            ageToInt == null -> false
            ageToInt in (MIN_AGE_VALUE..MAX_AGE_VALUE) -> true
            age.startsWith(INVALID_AGE_VALIDATOR_PREFIX) && age.length > INVALID_AGE_VALIDATOR_LENGTH -> false
            else -> false
        }
        _uiState.value =
            _uiState.value?.copy(
                ageValidationText = if (isValidAge) null else ageHelperText,
                isValidAge = isValidAge
            )
    }

    fun clearFields() {
        _uiState.value = AppUiState()
    }

    companion object {
        const val MIN_USERNAME_LENGTH = 10
        const val MIN_AGE_VALUE = 0
        const val MAX_AGE_VALUE = 130
        const val INVALID_AGE_VALIDATOR_PREFIX = "0"
        const val INVALID_AGE_VALIDATOR_LENGTH = 1
        const val NAME_PATTERN = "^[A-Za-z]*$"
        const val EMPTY_SPACE = " "
    }
}