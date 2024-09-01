package id.finale.countrivia.data.remote.retrofit

import id.finale.countrivia.data.local.model.entities.CountryModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryApi {
    @GET("all")
    suspend fun getCountry(): List<CountryModel>

    @GET("name/{nameCommon}")
    suspend fun getCountryDetail(
        @Path("{nameCommon") name: String
    ): Response<CountryModel>

    companion object{
        const val BASE_URL = "https://restcountries.com/v3.1/"
    }
}