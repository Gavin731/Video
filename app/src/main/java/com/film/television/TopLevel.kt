package com.film.television

import android.content.Context
import android.widget.Toast
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.film.television.utils.DataStoreUtil

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "juduo")

fun showShortToast(text: String) {
    Toast.makeText(MyApplication.INSTANCE, text, Toast.LENGTH_SHORT).show()
}

fun showLongToast(text: String) {
    Toast.makeText(MyApplication.INSTANCE, text, Toast.LENGTH_LONG).show()
}

suspend fun isTeenModeEnabled(): Boolean {
    return !DataStoreUtil.getPassword().isNullOrEmpty()
}