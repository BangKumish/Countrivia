package id.finale.countrivia.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import id.finale.countrivia.data.entities.CountryModel
import id.finale.countrivia.data.remote.Resource
import id.finale.countrivia.data.repository.CountryRepo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val countryRepo: CountryRepo
): ViewModel() {

    private val TAG = HomeViewModel::class.java.name

    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    private val _countries = MutableLiveData<Resource<List<CountryModel>?>>()
    val countries: LiveData<Resource<List<CountryModel>?>> = _countries

    init {
        fetchAndCacheData()
    }

    fun fetchAndCacheData() = viewModelScope.launch {
        _countries.value = Resource.Loading
        _countries.value = countryRepo.getAllCountries()
    }

    fun setSearchQuery(query: String){
        this._searchQuery.value = query
    }
}

//class CountryViewModel : ViewModel() {
//    private val retroInstance = RetroInstance.getService()
//
//    fun getCountryList(): MutableLiveData<ResponseCountry> {
//        val mutableLiveData = MutableLiveData<ResponseCountry>()
//        retroInstance.getCountryList().enqueue(object : Callback<ResponseCountry> {
//            override fun onResponse(
//                call: Call<ResponseCountry>,
//                response: Response<ResponseCountry>
//            ) {
//                if (response.isSuccessful) {
//                    mutableLiveData.value = response.body()
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseCountry>, t: Throwable) {
//                // Handle failure if needed
//            }
//        })
//        return mutableLiveData
//    }
//}



//    var liveDataList: MutableLiveData<List<CountryModel>> = MutableLiveData()
//    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>>{
//        return liveDataList
//    }
//    fun makeAPICall(){
//        val countryApi = CountryApi.getRetroInstance()
//        val retroService = countryApi.create(RetroServiceInterface::class.java)
//        val call = retroService.getCountryList()
//        call.enqueue(object : Callback<List<CountryModel>>{
//            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
//                //TODO
//            }
//            override fun onResponse(
//                call: Call<List<CountryModel>>,
//                response: Response<List<CountryModel>>
//            ) {
//                liveDataList.postValue(response.body())
//            }
//        })
//    }
