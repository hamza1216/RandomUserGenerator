package com.test.randomuser.repository

import com.test.randomuser.api.RetrofitClient
import com.test.randomuser.data.response.GetUsersResponse
import retrofit2.Callback

object ClientRepository {

    fun fetchUsers(count: Int, callback: Callback<GetUsersResponse>) {
        val call = RetrofitClient.apiInterface.getUsers(count)
        call.enqueue(callback)
    }
}