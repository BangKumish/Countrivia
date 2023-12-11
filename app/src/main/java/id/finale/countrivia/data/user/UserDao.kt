package id.finale.countrivia.data.user

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllUser(user: List<User>): List<Long>

    @Query("SELECT * FROM userTable WHERE email LIKE :email AND password LIKE :password")
    fun readLoginData(email: String, password: String): User

    @Query("SELECT * FROM userTable WHERE id LIKE :id")
    fun getUserDetail(id: Long): User

    @Query("DELETE FROM userTable")
    fun deleteAll()

    @Query("SELECT * FROM userTable ORDER BY id ASC")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM userTable WHERE email LIKE :email")
    fun getUserByEmail(email: String): User
}