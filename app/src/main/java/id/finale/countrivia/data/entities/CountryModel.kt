package id.finale.countrivia.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//{
//    "name": {
//      "common": "Indonesia",
//      "official": "Republic of Indonesia",
//      "nativeName": {
//        "ind": {
//          "official": "Republik Indonesia",
//          "common": "Indonesia"
//        }
//      }
//    },
//    "unMember": true,
//    "capital": [
//      "Jakarta"
//    ],
//    "region": "Asia",
//    "subregion": "South-Eastern Asia",
//    "flags": {
//      "png": "https://flagcdn.com/w320/id.png",
//      "svg": "https://flagcdn.com/id.svg",
//      "alt": "The flag of Indonesia is composed of two equal horizontal bands of red and white."
//    },
//    "population": 273523621,
//    "coatOfArms": {
//      "png": "https://mainfacts.com/media/images/coats_of_arms/id.png",
//      "svg": "https://mainfacts.com/media/images/coats_of_arms/id.svg"
//    }//
//  }

@Parcelize
class CountryModel(): Parcelable, Comparable<CountryModel>{
    var name: Name? = null
    var unMember: Boolean? = null
    var capital: ArrayList<String>? = null
    var region: String? = null
    var subregion: String? = null
    var flags: Flags? = null
    var population: Long? = null
    var coatOfArms: CoatOfArms?  = null

    override fun compareTo(other: CountryModel): Int {
        return name?.common?.let {
            other.name?.common?.compareTo(it)
        }?: kotlin.run { return 0 }
    }
}
