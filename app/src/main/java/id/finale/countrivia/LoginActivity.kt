package id.finale.countrivia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var userDao: UserDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        userDao = (application as MyApplication).database.userDao()

        GlobalScope.launch(Dispatchers.IO) {
            loginUser("example@email.com", "password123")
        }
    }

    private suspend fun loginUser(email: String, password: String) {
        val user = userDao.readLoginData(email, password)
        // Proses hasil login
        user.let {
        } ?: run {

        }
    }
}
