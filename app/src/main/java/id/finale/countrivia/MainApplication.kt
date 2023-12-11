package id.finale.countrivia

import android.app.Application
import androidx.room.Room
import dagger.hilt.android.HiltAndroidApp
import id.finale.countrivia.data.user.UserDatabase

@HiltAndroidApp
class MainApplication: Application() {

    lateinit var database: UserDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "userDatabase"
        ).allowMainThreadQueries().build()
    }
}