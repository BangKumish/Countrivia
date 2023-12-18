package id.finale.countrivia.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.databinding.OnboardingActivityBinding

@AndroidEntryPoint
class OnBoarding : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  binding = OnboardingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
