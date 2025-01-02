package com.examole.feature_mechanic.data.datasoruce

import android.content.Context
import com.examole.feature_mechanic.data.model.MechanicDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonDataSoruce(private val context: Context) {

    private val jsonFileName = "movers.json"

    fun getMovers(): List<MechanicDTO> {
        val jsonString = context.assets.open(jsonFileName).bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<MechanicDTO>>() {}.type
        return Gson().fromJson(jsonString, type)

    }

}