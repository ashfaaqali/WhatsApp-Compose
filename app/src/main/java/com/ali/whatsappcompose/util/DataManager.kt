package com.ali.whatsappcompose.util

import android.content.Context
import com.google.gson.Gson

object DataManager {

    fun fetchDataFromJson(context: Context) {
        val inputStream = context.assets.open("recentChats.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
    }

}