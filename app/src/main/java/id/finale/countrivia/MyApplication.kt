package id.finale.countrivia

import android.app.Application
import androidx.room.Room
import id.finale.countrivia.UserDatabase

class MyApplication : Application() {

    lateinit var database: UserDatabase
        private set

    override fun onCreate() {
        super.onCreate()

        database = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "user_database"
        ).fallbackToDestructiveMigration().build()
    }
}
