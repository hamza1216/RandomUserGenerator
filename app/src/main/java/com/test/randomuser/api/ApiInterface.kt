package com.test.randomuser.api

import com.test.randomuser.data.response.GetUsersResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/api")
    fun getUsers(@Query("results") count: Int) : Call<GetUsersResponse>

}