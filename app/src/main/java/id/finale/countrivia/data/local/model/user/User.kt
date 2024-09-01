package id.finale.countrivia.data.local.model.user

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
    var isActive: Int,
//    val profilePhoto: Bitmap? = null
)
