package id.finale.countrivia.onBoarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.activity.LoginActivity
import id.finale.countrivia.databinding.OnboardingActivityBinding
import id.finale.countrivia.onBoarding.onBoardingHelper.domain.OnBoardingPrefManager

@AndroidEntryPoint
class OnBoarding : AppCompatActivity() {

    private lateinit var prefManager: OnBoardingPrefManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  binding = OnboardingActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun gotoLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
