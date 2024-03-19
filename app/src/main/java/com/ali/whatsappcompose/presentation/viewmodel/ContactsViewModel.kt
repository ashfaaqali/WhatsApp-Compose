package com.ali.whatsappcompose.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ali.whatsappcompose.data.model.Contacts
import org.json.JSONObject

class ContactsViewModel : ViewModel() {
    private val _contacts = MutableLiveData<List<Contacts>>()
    val contacts: LiveData<List<Contacts>> = _contacts

    fun loadContactsFromAssets(context: Context, fileName: String) {
        val jsonString = readJsonFromAssets(context, fileName)
        val contactsArray = JSONObject(jsonString).getJSONArray("contacts")
        val contactsList = mutableListOf<Contacts>()
        for (i in 0 until contactsArray.length()) {
            val contactObject = contactsArray.getJSONObject(i)
            val contact = Contacts(
                id = contactObject.getInt("id"),
                name = contactObject.getString("name"),
                status = contactObject.getString("status")
            )
            contactsList.add(contact)
        }
        _contacts.value = contactsList
    }

    private fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    }
}