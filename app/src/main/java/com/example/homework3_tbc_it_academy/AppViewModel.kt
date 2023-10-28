package com.example.homework3_tbc_it_academy

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AppViewModel: ViewModel() {
    private val _uiState = MutableLiveData<ValidationUiState>()
    val uiState: LiveData<ValidationUiState> = _uiState

    init {
        _uiState.value = ValidationUiState()
    }

    fun validateEmail(email: String) {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _uiState.value =
            _uiState.value?.copy(emailValidationText = if (isValidEmail) null else "Invalid E-Mail Address.")
    }

    fun validateUsername(username: String) {
        val isValidUsername = username.isNotBlank() && username.length >= MIN_USERNAME_LENGTH
        _uiState.value =
            _uiState.value?.copy(usernameValidationText = if (isValidUsername) null else "Username should contain at least 10 characters.")
    }

    fun validateFirstName(firstName: String) {
        val isValidName = firstName.isNotBlank() && firstName.matches(Regex("^[A-Za-z]*$"))
        _uiState.value =
            _uiState.value?.copy(firstNameValidationText = if (isValidName) null else "First name should contain at least 1 alphabetic character. Numbers are not allowed.")
    }

    fun validateLastName(lastName: String) {
        val isValidName = lastName.isNotBlank() && lastName.matches(Regex("^[A-Za-z]*$"))
        _uiState.value =
            _uiState.value?.copy(lastNameValidationText = if (isValidName) null else "Last name should contain at least 1 alphabetic character. Numbers are not allowed.")
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
            _uiState.value?.copy(ageValidationText = if (isValidAge) null else "Please type in a valid number.")
    }

    fun clearInputFieldHelperTexts() {
        _uiState.value = ValidationUiState()
    }

    companion object {
        const val MIN_USERNAME_LENGTH = 3
        const val MIN_AGE_VALUE = 0
        const val MAX_AGE_VALUE = 130
        const val INVALID_AGE_VALIDATOR_PREFIX = "0"
        const val INVALID_AGE_VALIDATOR_LENGTH = 1
    }
}

/**
 * “Save” ღილაკზე დაჭერისას უნდა შემოწმდეს:
 *
 * შევსებულია თუ არა ყველა ველი.
 *
 * Username ში შეყვანილი სიმბოლოების რაოდენობა არ უნდა იყოს 10-ზე ნაკლები.
 *
 * ვალიდურია თუ არა ელექტრონული ფოსტა.
 *
 * “Age” უნდა იყოს მთელი დადებითი რიცხვი.
 *
 * რომელიმე პირობის შეუსრულებლობის შემთხვევაში გამოტანეთ ეკრანზე შესაბამისი ტექსტური შეტყიბინება.
 *
 * “Clear” ღილაკზე დაჭერისას ველები უნდა გასუფთავდეს. აღნიშული ოპერაცია უნდა სრულდებოდეს მხოლოდ იმ შემთხვევაში თუ მომხარებელმა ღილაკს დააჭირა ხანგრძლივად.
 */