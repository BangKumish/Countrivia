package id.finale.countrivia.retrofit

import id.finale.countrivia.data.CountryModel
import retrofit2.Call
import retrofit2.http.GET

interface RetroServiceInterface {
    @GET("all")
    fun getCountryList(): Call<List<CountryModel>>
}