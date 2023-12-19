package id.finale.countrivia.ui.dashboard

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.finale.countrivia.di.data.user.UserDao
import id.finale.countrivia.di.data.user.UserDatabase
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private val userDao = UserDatabase.getDatabase(application).userDao()


    fun getUserDao(): UserDao {
        return userDao
    }
}