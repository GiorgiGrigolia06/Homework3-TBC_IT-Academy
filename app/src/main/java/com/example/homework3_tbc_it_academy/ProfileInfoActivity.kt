package com.example.homework3_tbc_it_academy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework3_tbc_it_academy.databinding.ActivityProfileInfoBinding

class ProfileInfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.emailProfile.text = getString(R.string.email_profile_view, intent.getStringExtra("email"))
        binding.usernameProfile.text = getString(R.string.username_profile_view, intent.getStringExtra("username"))
        binding.nameProfile.text = getString(R.string.full_name_profile_view, intent.getStringExtra("fullName"))
        binding.ageProfile.text = getString(R.string.age_profile_view, intent.getStringExtra("age"))

        binding.againButton.setOnClickListener {
            finish()
        }
    }
}