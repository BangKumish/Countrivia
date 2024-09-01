package id.finale.countrivia.di.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import id.finale.countrivia.data.remote.CountryClient
import id.finale.countrivia.data.remote.retrofit.CountryApi
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideCountryAPI(
        countryClient: CountryClient,
    ): CountryApi {
        return countryClient.buildApi(CountryApi::class.java)
    }

//   private val client: OkHttpClient
//       get(){
//           val interceptor = HttpLoggingInterceptor()
//           interceptor.level = HttpLoggingInterceptor.Level.BODY
//
//           return OkHttpClient.Builder()
//               .addInterceptor(interceptor)
//               .build()
//       }
//
//    @Singleton
//    @Provides
//    fun provideRetrofitInterface(): CountryApi {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(client)
//            .addConverterFactory(
//                GsonConverterFactory.create()
//            ).build().create(CountryApi::class.java)
//    }
}