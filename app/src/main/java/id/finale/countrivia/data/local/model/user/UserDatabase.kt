package id.finale.countrivia.data.local.model.user

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 5, exportSchema = false)
//@TypeConverters(BitmapConverter::class)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "userDatabase"
                ).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}