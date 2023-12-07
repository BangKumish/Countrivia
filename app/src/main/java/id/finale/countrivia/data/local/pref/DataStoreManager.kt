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
    private val Context.dataStore: DataStore<Preferences>
    by preferencesDataStore(name = COUNTRY_DATASTORE)

    companion object {
        val COUNTRY_KEY = stringPreferencesKey("countryJson")
        val KEY_LAST_FETCH_TIME = stringPreferencesKey("keyLastFetchTime")
        private const val COUNTRY_DATASTORE = "datastore"
    }

    suspend fun saveCountriesJSONString(countriesJSON: String) {
        context.dataStore.edit {
            it[COUNTRY_KEY] = countriesJSON
        }
    }

    suspend fun getCountriesJSONString(): String {
        return context.dataStore.data.first()[COUNTRY_KEY] ?: ""
    }


    suspend fun saveLastFetchTime(lastFetchTime: Int) {
        context.dataStore.edit {
            it[KEY_LAST_FETCH_TIME] = lastFetchTime.toString()
        }
    }

    fun getLastFetchTime() = context.dataStore.data.map {
        it[KEY_LAST_FETCH_TIME]?.toInt() ?: 0
    }
}