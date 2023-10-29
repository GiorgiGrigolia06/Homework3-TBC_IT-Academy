package com.example.homework3_tbc_it_academy

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3_tbc_it_academy.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Observes input fields and if some of the inputs are not validated shows the message.
        viewModel.uiState.observe(this) {
            binding.apply {
                emailInput.helperText = it.emailValidationText
                username.helperText = it.usernameValidationText
                firstName.helperText = it.firstNameValidationText
                lastName.helperText = it.lastNameValidationText
                age.helperText = it.ageValidationText
            }
        }

        // Validates all input fields using different validation functions from view model.
        binding.saveButton.setOnClickListener {
            currentFocus?.clearFocus()
            validateInputOnSaveClick(binding.emailInputEditText, viewModel::validateEmail)
            validateInputOnSaveClick(binding.usernameInputEditText, viewModel::validateUsername)
            validateInputOnSaveClick(binding.firstNameInputEditText, viewModel::validateFirstName)
            validateInputOnSaveClick(binding.lastNameInputEditText, viewModel::validateLastName)
            validateInputOnSaveClick(binding.ageInputEditText, viewModel::validateAge)

            // If all inputs are validated, saves them, and navigates to another activity, which shows the user input.
            if (
                viewModel.uiState.value?.isValidEmail == true &&
                viewModel.uiState.value?.isValidUsername == true &&
                viewModel.uiState.value?.isValidFirstName == true &&
                viewModel.uiState.value?.isValidLastName == true &&
                viewModel.uiState.value?.isValidAge == true
            ) {
                val intent = Intent(this, ProfileInfoActivity::class.java)
                intent.putExtra(EMAIL, binding.emailInputEditText.text.toString())
                intent.putExtra(USERNAME, binding.usernameInputEditText.text.toString())
                intent.putExtra(
                    FULL_NAME,
                    "${binding.firstNameInputEditText.text.toString()} ${binding.lastNameInputEditText.text.toString()}"
                )
                intent.putExtra(AGE, binding.ageInputEditText.text.toString())

                startActivity(intent)
            }
        }

        // Clears every input and, if present, validation message.
        binding.clearButton.setOnLongClickListener {
            currentFocus?.clearFocus()
            binding.apply {
                emailInputEditText.text?.clear()
                usernameInputEditText.text?.clear()
                firstNameInputEditText.text?.clear()
                lastNameInputEditText.text?.clear()
                ageInputEditText.text?.clear()
            }
            viewModel.clearFields()
            true
        }
    }

    companion object {
        const val EMAIL = "email"
        const val USERNAME = "username"
        const val FULL_NAME = "fullName"
        const val AGE = "age"
    }
}

private fun validateInputOnSaveClick(input: TextInputEditText, validator: (String) -> Unit) {
    val text = input.text.toString()
    validator(text)
}