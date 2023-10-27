package com.example.homework3_tbc_it_academy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.homework3_tbc_it_academy.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

/****************************************
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