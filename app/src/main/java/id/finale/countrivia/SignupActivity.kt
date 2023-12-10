package id.finale.countrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SignupActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        userDao = (application as MyApplication).database.userDao()

        GlobalScope.launch(Dispatchers.IO) {
            signupUser("newuser@email.com", "newusername", "newnim", "newpassword123")
        }
    }

    private suspend fun signupUser(email: String, username: String, nim: String, password: String) {
        val existingUser = userDao.getUserByEmail(email)
        if (existingUser == null) {
            val newUser = EntityUser(email = email, username = username, nim = nim, password = password)

            userDao.insertUser(newUser)

        } else {
        }
    }
}
