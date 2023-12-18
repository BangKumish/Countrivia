package id.finale.countrivia.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val email: String,
    val userName: String,
    val nim: String,
    val password: String,
    var isActive: Boolean = false
)
