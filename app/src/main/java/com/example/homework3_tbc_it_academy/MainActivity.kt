package com.example.homework3_tbc_it_academy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.homework3_tbc_it_academy.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: AppViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        validateInputOnFocusChange(binding.emailInputEditText, viewModel::validateEmail)
        validateInputOnFocusChange(binding.usernameInputEditText, viewModel::validateUsername)
        validateInputOnFocusChange(binding.firstNameInputEditText, viewModel::validateFirstName)
        validateInputOnFocusChange(binding.lastNameInputEditText, viewModel::validateLastName)
        validateInputOnFocusChange(binding.ageInputEditText, viewModel::validateAge)

        viewModel.uiState.observe(this) {
            with(binding) {
                emailInput.helperText = it.emailValidation
                username.helperText = it.usernameValidation
                firstName.helperText = it.firstNameValidation
                lastName.helperText = it.lastNameValidation
                age.helperText = it.ageValidation
            }
        }

        binding.saveButton.setOnClickListener {
            validateInputOnSaveClick(binding.emailInputEditText, viewModel::validateEmail)
            validateInputOnSaveClick(binding.usernameInputEditText, viewModel::validateUsername)
            validateInputOnSaveClick(binding.firstNameInputEditText, viewModel::validateFirstName)
            validateInputOnSaveClick(binding.lastNameInputEditText, viewModel::validateLastName)
            validateInputOnSaveClick(binding.ageInputEditText, viewModel::validateAge)
        }

        binding.clearButton.setOnClickListener {
            with(binding) {
                emailInputEditText.text?.clear()
                usernameInputEditText.text?.clear()
                firstNameInputEditText.text?.clear()
                lastNameInputEditText.text?.clear()
                ageInputEditText.text?.clear()
            }

            viewModel.clearInputFieldHelperTexts()
        }
    }
}

private fun validateInputOnFocusChange(input: TextInputEditText, validator: (String) -> Unit) {
    input.setOnFocusChangeListener { _, hasFocus ->
        if (!hasFocus) {
            val text = input.text.toString()
            validator(text)
        }
    }
}

private fun validateInputOnSaveClick(input: TextInputEditText, validator: (String) -> Unit) {
    val text = input.text.toString()
    validator(text)
}