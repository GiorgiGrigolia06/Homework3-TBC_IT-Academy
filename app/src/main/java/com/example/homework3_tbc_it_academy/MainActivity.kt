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

        viewModel.uiState.observe(this) {
            binding.apply {
                emailInput.helperText = it.emailValidationText
                username.helperText = it.usernameValidationText
                firstName.helperText = it.firstNameValidationText
                lastName.helperText = it.lastNameValidationText
                age.helperText = it.ageValidationText
            }
        }

        binding.saveButton.setOnClickListener {
            validateInputOnSaveClick(binding.emailInputEditText, viewModel::validateEmail)
            validateInputOnSaveClick(binding.usernameInputEditText, viewModel::validateUsername)
            validateInputOnSaveClick(binding.firstNameInputEditText, viewModel::validateFirstName)
            validateInputOnSaveClick(binding.lastNameInputEditText, viewModel::validateLastName)
            validateInputOnSaveClick(binding.ageInputEditText, viewModel::validateAge)
            currentFocus?.clearFocus()
        }

        binding.clearButton.setOnLongClickListener {
            binding.apply {
                emailInputEditText.text?.clear()
                usernameInputEditText.text?.clear()
                firstNameInputEditText.text?.clear()
                lastNameInputEditText.text?.clear()
                ageInputEditText.text?.clear()
            }
            currentFocus?.clearFocus()
            viewModel.clearInputFieldHelperTexts()
            true
        }
    }
}

private fun validateInputOnSaveClick(input: TextInputEditText, validator: (String) -> Unit) {
    val text = input.text.toString()
    validator(text)
}