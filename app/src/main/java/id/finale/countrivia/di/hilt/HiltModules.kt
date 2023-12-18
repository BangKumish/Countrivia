package id.finale.countrivia.di.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.finale.countrivia.di.retrofit.CountryApi
import id.finale.countrivia.di.retrofit.CountryApi.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HiltModules {
   private val client: OkHttpClient
       get(){
           val interceptor = HttpLoggingInterceptor()
           interceptor.level = HttpLoggingInterceptor.Level.BODY

           return OkHttpClient.Builder()
               .addInterceptor(interceptor)
               .build()
       }

    @Singleton
    @Provides
    fun provideRetrofitInterface(): CountryApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            ).build().create(CountryApi::class.java)
    }
}