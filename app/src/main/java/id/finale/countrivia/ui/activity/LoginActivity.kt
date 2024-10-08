package id.finale.countrivia.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.databinding.ActivityLoginBinding
import id.finale.countrivia.data.local.model.user.UserDao
import id.finale.countrivia.data.local.model.user.UserDatabase
import id.finale.countrivia.ui.MainActivity

@AndroidEntryPoint
class LoginActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var userDao: UserDao
    private lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {

        userDatabase = UserDatabase.getDatabase(this)
        userDao = userDatabase.userDao()
        binding = ActivityLoginBinding.inflate(layoutInflater)

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

        binding.loginGuest.setOnClickListener {
            loginAsGuest()
        }
    }

    private fun loginUser(email: String, password: String){
        val user = userDao.readLoginData(email, password)
        if(user == null){
            Toast.makeText(this, "Email atau Password Salah!", Toast.LENGTH_LONG).show()
        } else{
            user.isActive = 1
            Toast.makeText(this, "Login Berhasil!", Toast.LENGTH_LONG).show()
            userDao.loginUser(email)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginAsGuest(){
        Toast.makeText(this, "Coming Soon!", Toast.LENGTH_LONG).show()
//        userDao.deActiveUser()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
    }
}