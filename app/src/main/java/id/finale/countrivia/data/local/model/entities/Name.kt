package id.finale.countrivia.data.local.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Name(
    val official: String,
    val common: String
): Parcelable
