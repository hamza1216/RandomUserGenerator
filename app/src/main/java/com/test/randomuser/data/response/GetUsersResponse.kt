package com.test.randomuser.data.response

import com.google.gson.JsonArray
import com.google.gson.JsonObject

data class GetUsersResponse (
    val results: JsonArray?,
    val info: JsonObject?,
    val error: String?
)