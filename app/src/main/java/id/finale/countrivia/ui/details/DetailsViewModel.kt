package id.finale.countrivia.ui.details

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import id.finale.countrivia.di.data.entities.CountryModel
import id.finale.countrivia.di.extensions.prettyCount
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : ViewModel() {
    val countryModel = ObservableField<CountryModel>()

    fun getCountryDetail(): String{
        val countryInfo = countryModel.get()!!
        val stringBuilder = StringBuilder()
        stringBuilder.append(countryInfo.name?.common)
        stringBuilder.append(" officially known as ")
        stringBuilder.append(countryInfo.name?.official)
        stringBuilder.append(" located in the region of ")
        stringBuilder.append(countryInfo.region)
        stringBuilder.append(" sub region is ")
        stringBuilder.append(countryInfo.subregion + ".")
        stringBuilder.append("Total population of this country is ")
        stringBuilder.append(countryInfo.population?.prettyCount())
        stringBuilder.append(". ")
        stringBuilder.append(countryInfo.name?.common)
        if (countryInfo.unMember == true) {
            stringBuilder.append(" is a UN member country. ")
        }
        else
            stringBuilder.append(" is unfortunately not a UN member country yet. ")

        countryInfo.capital?.let {
            stringBuilder.append("Capital(s) of this country is ")

            for ((i, capital) in it.withIndex()) {
                stringBuilder.append(capital)
                if (it.size > 1 && i < it.size - 1)
                    stringBuilder.append(" , ")
            }
            stringBuilder.append(".")
        }
        return stringBuilder.toString()
    }
}