package com.abhigyan.trustmebro.ui.utils

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.abhigyan.trustmebro.calculator.types.Event
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

object CustomNavType {
    val EventType =object: NavType<Int>(false){
        override fun get(bundle: Bundle, key: String): Int? {
            return bundle.getString(key)?.toInt()
        }

        override fun parseValue(value: String): Int {
            return value.toInt()
        }

        override fun put(bundle: Bundle, key: String, value: Int) {
            bundle.putString(key,value.toString())
        }
    }
}