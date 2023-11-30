package id.finale.countrivia.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {
    companion object{
        private const val BASE_URL = "https://restcountries.com/v3.1/"

        fun getRetroInstance(): Retrofit{
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun getService(): RetroServiceInterface{
            return getRetroInstance().create(RetroServiceInterface::class.java)
        }
    }
}