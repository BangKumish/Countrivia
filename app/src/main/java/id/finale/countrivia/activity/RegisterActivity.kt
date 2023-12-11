package id.finale.countrivia.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.MainActivity
import id.finale.countrivia.MainApplication
import id.finale.countrivia.data.user.User
import id.finale.countrivia.data.user.UserDao
import id.finale.countrivia.databinding.ActivityRegisterBinding

@AndroidEntryPoint
class RegisterActivity : AppCompatActivity(){
    private lateinit var userDao: UserDao
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        userDao = (application as MainApplication).database.userDao()


        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.tvToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignup.setOnClickListener {
            val email = binding.edtEmailSignup.text.toString()
            val username = binding.edtUsernameSignup.text.toString()
            val nim = binding.edtNimSignup.text.toString()
            val password = binding.edtPasswordSignup.text.toString()


            if (username.isEmpty()){
                binding.edtEmailSignup.requestFocus()
                binding.edtUsernameSignup.error = "Username harus diisi!"
                binding.edtNimSignup.requestFocus()
                binding.edtPasswordSignup.requestFocus()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                binding.edtEmailSignup.error = "Email harus diisi!"
                binding.edtUsernameSignup.requestFocus()
                binding.edtNimSignup.requestFocus()
                binding.edtPasswordSignup.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                binding.edtEmailSignup.requestFocus()
                binding.edtUsernameSignup.error = "Berikan Email yang Valid!"
                binding.edtNimSignup.requestFocus()
                binding.edtPasswordSignup.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                binding.edtEmailSignup.requestFocus()
                binding.edtUsernameSignup.requestFocus()
                binding.edtNimSignup.requestFocus()
                binding.edtPasswordSignup.error = "Password harus diisi!"
                return@setOnClickListener
            }
            if (password.length < 6){
                binding.edtEmailSignup.requestFocus()
                binding.edtUsernameSignup.requestFocus()
                binding.edtNimSignup.requestFocus()
                binding.edtPasswordSignup.error = "Password harus lebih dari 6 Karakter!"
                return@setOnClickListener
            }

            registerUser(email, username, nim, password)
        }
    }

    private fun registerUser(
        email: String,
        username: String,
        nim: String,
        password: String) {
        val existingUser = userDao.getUserByEmail(email)
        if(existingUser == null){
            val newUser = User(
                email = email,
                userName = username,
                nim = nim,
                password = password
            )
            userDao.addUser(newUser)
            Toast.makeText(this, "Registrasi Sukses!", Toast.LENGTH_LONG).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else{
            Toast.makeText(this, "Email telah terpakai", Toast.LENGTH_LONG).show()
        }
    }
}