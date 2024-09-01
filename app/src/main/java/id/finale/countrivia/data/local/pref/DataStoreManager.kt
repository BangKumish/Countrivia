package id.finale.countrivia.data.local.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


@ActivityRetainedScoped
class DataStoreManager @Inject constructor(
    @ApplicationContext private val context: Context
){
//    CountryRepo DataStore
    private val Context.dataStore: DataStore<Preferences>
    by preferencesDataStore(name = COUNTRY_DATASTORE)


    //    OnBoarding SharedPreferences
//    private val pref: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
//    private val editor: SharedPreferences.Editor = pref.edit()

    companion object {
//        OnBoarding SharedPreference Value
        private const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"
        private const val PREF_NAME = "PREF_NAME"

//        CountryRepo DataStore Value
        val COUNTRY_KEY = stringPreferencesKey("countryJson")
        val KEY_LAST_FETCH_TIME = stringPreferencesKey("keyLastFetchTime")
        private const val COUNTRY_DATASTORE = "datastore"
    }

//    CountryRepo DataStore Functions
    suspend fun saveCountriesJSONString(countriesJSON: String) {
        context.dataStore.edit {preferences ->
            preferences[COUNTRY_KEY] = countriesJSON
        }
    }

    suspend fun getCountriesJSONString(): String {
        return context.dataStore.data.first()[COUNTRY_KEY] ?: ""
    }


    suspend fun saveLastFetchTime(lastFetchTime: Int) {
        context.dataStore.edit {preferences ->
            preferences[KEY_LAST_FETCH_TIME] = lastFetchTime.toString()
        }
    }

    fun getLastFetchTime() = context.dataStore.data.map {
        it[KEY_LAST_FETCH_TIME]?.toInt() ?: 0
    }

//    OnBoarding Shared Preference Variable
    var isFirstTimeLaunch: Boolean
        get() = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            .getBoolean(IS_FIRST_TIME_LAUNCH, true)
        set(isFirstTime){
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
                .edit()
                .putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
                .apply()
        }

//    var isFirstTimeLaunch: Boolean
//        get(){
//            return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true)
//        } set(isFirstTime){
//            editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime)
//            editor.commit()
//        }
}