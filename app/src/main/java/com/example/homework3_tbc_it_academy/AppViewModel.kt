package com.example.homework3_tbc_it_academy

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ValidationUiState(
    val emailValidation: String? = null,
    val usernameValidation: String? = null,
    val firstNameValidation: String? = null,
    val lastNameValidation: String? = null,
    val ageValidation: String? = null
)

class AppViewModel : ViewModel() {
    private val _uiState = MutableLiveData<ValidationUiState>()
    val uiState: LiveData<ValidationUiState> = _uiState

    init {
        _uiState.value = ValidationUiState()
    }

    fun validateEmail(email: String) {
        val isValidEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        _uiState.value =
            _uiState.value?.copy(emailValidation = if (isValidEmail) null else "Invalid Email Address")
    }

    fun validateUsername(username: String) {
        val isValidUsername = username.length >= 10
        _uiState.value =
            _uiState.value?.copy(usernameValidation = if (!isValidUsername) "Username should contain at least 10 characters" else null)
    }

    fun validateFirstName(firstName: String) {
        val isValidName = firstName.isNotEmpty()
        _uiState.value =
            _uiState.value?.copy(firstNameValidation = if (!isValidName) "First name should contain at least 1 character" else null)
    }

    fun validateLastName(lastName: String) {
        val isValidName = lastName.isNotEmpty()
        _uiState.value =
            _uiState.value?.copy(lastNameValidation = if (!isValidName) "Last name should contain at least 1 character" else null)
    }

    fun validateAge(age: String) {
        val ageToInt = age.toIntOrNull()
        val isValidAge = when {
            ageToInt == null -> false
            ageToInt in 0..130 -> true
            age.startsWith("0") && age.length > 1 -> false
            else -> false
        }
        _uiState.value =
            _uiState.value?.copy(ageValidation = if (isValidAge) null else "Please type in a valid number")
    }

    fun clearInputFieldHelperTexts() {
        _uiState.value = ValidationUiState()
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