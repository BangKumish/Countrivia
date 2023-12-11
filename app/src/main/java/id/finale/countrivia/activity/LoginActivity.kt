package id.finale.countrivia.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.MainActivity
import id.finale.countrivia.MainApplication
import id.finale.countrivia.data.user.UserDao
import id.finale.countrivia.databinding.ActivityLoginBinding

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityLoginBinding.inflate(layoutInflater)
        userDao = (application as MainApplication).database.userDao()

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.loginToRegis.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edtEmailLogin.text.toString()
            val password: String = binding.edtPasswordLogin.text.toString()

            if (email.isEmpty()){
                binding.edtEmailLogin.error = "Email Harus Diisi"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailLogin.error = "Email Tidak Valid"
                binding.edtPasswordLogin.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.edtEmailLogin.requestFocus()
                binding.edtPasswordLogin.error = "Password Harus Diisi"
                return@setOnClickListener
            }

            loginUser(email, password)
        }

    }

    private fun loginUser(email: String, password: String){
        val user = userDao.readLoginData(email, password)
        if(user == null){
            Toast.makeText(this, "Email atau Password Salah!", Toast.LENGTH_LONG).show()
        } else{
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}