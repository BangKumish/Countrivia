package id.finale.countrivia.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import id.finale.countrivia.databinding.ActivityEditBinding
import id.finale.countrivia.data.local.model.user.UserDao
import id.finale.countrivia.data.local.model.user.UserDatabase
import id.finale.countrivia.ui.MainActivity

@AndroidEntryPoint
class EditProfile : AppCompatActivity()  {

    private lateinit var binding: ActivityEditBinding
    private lateinit var userDao: UserDao
    private lateinit var userDatabase: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        userDatabase = UserDatabase.getDatabase(this)
        userDao = userDatabase.userDao()
        binding = ActivityEditBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getUser()
        setupListener()
    }

    private fun setupListener(){
        binding.btnUpdate.setOnClickListener {
            userDao.updateUser(
                    binding.editEmail.text.toString(),
                    binding.editUsername.text.toString(),
                    binding.editNIM.text.toString(),
                    binding.editPassword.text.toString()
            )
        }

        binding.btnBackToDashboard.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getUser(){
        val user = userDao.getActiveUser()
        binding.editUsername.setText(user.userName)
        binding.editEmail.setText(user.email)
        binding.editNIM.setText(user.nim)
        binding.editPassword.setText(user.password)
    }
}