package com.example.feature_mover.data.datasoruce

import android.content.Context
import com.example.feature_mover.data.model.MoverDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonDataSoruce(private val context: Context) {

    private val jsonFileName = "movers.json"

    fun getMovers(): List<MoverDTO> {
        val jsonString = context.assets.open(jsonFileName).bufferedReader().use { it.readText() }
        val type = object : TypeToken<List<MoverDTO>>() {}.type
        return Gson().fromJson(jsonString, type)

    }

}