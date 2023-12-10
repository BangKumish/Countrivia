package id.finale.countrivia

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    suspend fun insertUser(user: EntityUser)

    @Query("SELECT * FROM users WHERE email LIKE :email AND password LIKE :password")
    fun readLoginData(email: String, password: String):EntityUser

    @Query("SELECT * FROM users WHERE email LIKE :email")
    fun getUserByEmail(email: String):EntityUser
}
