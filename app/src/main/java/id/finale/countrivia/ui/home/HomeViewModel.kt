package id.finale.countrivia.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import id.finale.countrivia.data.CountryModel
import id.finale.countrivia.retrofit.RetroInstance
import id.finale.countrivia.retrofit.RetroServiceInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


class HomeViewModel : ViewModel() {

    var liveDataList: MutableLiveData<List<CountryModel>> = MutableLiveData()
    fun getLiveDataObserver(): MutableLiveData<List<CountryModel>>{
        return liveDataList
    }
    fun makeAPICall(){
        val retroInstance = RetroInstance.getRetroInstance()
        val retroService = retroInstance.create(RetroServiceInterface::class.java)
        val call = retroService.getCountryList()
        call.enqueue(object : Callback<List<CountryModel>>{
            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {
                //TODO
            }
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                liveDataList.postValue(response.body())
            }
        })
    }
}