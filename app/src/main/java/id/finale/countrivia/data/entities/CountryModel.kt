package id.finale.countrivia.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class CountryModel(): Parcelable, Comparable<CountryModel>{
    var name: Name? = null
    var capital: ArrayList<String>? = null
    var flags: Flags? = null
    var population: Long? = null

    override fun compareTo(other: CountryModel): Int {
        return name?.common?.let {
            other.name?.common?.compareTo(it)
        }?: kotlin.run { return 0 }
    }
}
