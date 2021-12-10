package com.test.randomuser.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.test.randomuser.BuildConfig
import com.test.randomuser.data.model.User
import com.test.randomuser.data.response.GetUsersResponse
import com.test.randomuser.repository.ClientRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception


class UsersViewModel : ViewModel() {

    val users = MutableLiveData<MutableList<User>>().apply { value = mutableListOf() }

    val message = MutableLiveData<String>()

    val loading = MutableLiveData<Boolean>()


    fun fetchUsers(count: Int) {
        loading.value = true

        ClientRepository.fetchUsers(count, object: Callback<GetUsersResponse> {
            override fun onFailure(call: Call<GetUsersResponse>, error: Throwable) {
                loading.value = false
                message.value = error.message
            }

            override fun onResponse(call: Call<GetUsersResponse>, response: Response<GetUsersResponse>) {
                loading.value = false

                val data = response.body()?.let { it } ?: return

                if (data.error?.isNotEmpty() == true) {
                    message.value = data.error
                    return
                }

                try {
                    val result = data.results
                    val list: MutableList<User> = users.value?.toMutableList() ?: mutableListOf()
                    for (item in result!!) {
                        try {
                            val userObj = item as JsonObject
                            val nameObj = userObj.getAsJsonObject("name")
                            val locationObj = userObj.getAsJsonObject("location")
                            val pictureObj = userObj.getAsJsonObject("picture")

                            val gender = userObj.get("gender").asString
                            val firstName = nameObj.get("first").asString
                            val lastName = nameObj.get("last").asString
                            val location = locationObj.get("country").asString
                            val picture = pictureObj.get("medium").asString
                            val user = User(gender, firstName, lastName, location, picture)
                            list.add(user)
                        }
                        catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }

                    Log.e("onResponse", "list : $list")
                    users.value = list

                } catch (e: Exception) {
                    message.value = e.message
                }
            }
        })
    }
}