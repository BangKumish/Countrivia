package id.finale.countrivia.di.data.repository

import com.google.gson.Gson
import id.finale.countrivia.di.data.entities.Countries
import id.finale.countrivia.di.data.entities.CountryModel
import id.finale.countrivia.di.data.local.pref.DataStoreManager
import id.finale.countrivia.di.data.remote.Resource
import id.finale.countrivia.di.data.remote.SafeApiCall
import id.finale.countrivia.di.retrofit.CountryApi
import id.finale.countrivia.di.extensions.Constant.Companion.FETCH_INTERVAL_IN_SEC
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class CountryRepo @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val countryApi: CountryApi
): SafeApiCall {

    private suspend fun getAllCountriesFromDataStore(): List<CountryModel> {
        val data: String = dataStoreManager.getCountriesJSONString()
        return Gson().fromJson(data, Countries::class.java).countries
    }

    suspend fun getAllCountries(): Resource<List<CountryModel>> {
        if(isFetchNeeded(
            dataStoreManager.getLastFetchTime().first()
        )){
            val response: Resource<List<CountryModel>> = safeApiCall {
                countryApi.getCountry()
            }
            if(response is Resource.Success){
                response.let {
                    val now = System.currentTimeMillis() / 1000L
                    dataStoreManager.saveLastFetchTime(now.toInt())
                    (it.value as ArrayList<CountryModel>).sorted()
                    val dataString: String = Gson().toJson(Countries(it.value))
                    dataStoreManager.saveCountriesJSONString(dataString)
                }
            }
            return response
        } else {
            return safeApiCall {
                getAllCountriesFromDataStore()
            }
        }
    }

    private fun isFetchNeeded(lastFetchTime: Int): Boolean{
        if(lastFetchTime == 0){
            return true
        }
        val currentTime = System.currentTimeMillis()/1000L
        if(currentTime - lastFetchTime >= FETCH_INTERVAL_IN_SEC){
            return true
        }
        return false
    }
}

