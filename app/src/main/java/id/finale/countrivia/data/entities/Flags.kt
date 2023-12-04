package id.finale.countrivia.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Flags(
    val png: String = "",
    val alt: String = ""
): Parcelable
