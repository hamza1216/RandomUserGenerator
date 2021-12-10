package com.test.randomuser.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    val email: String,
    val username: String,
    val phone: String,
    val cell: String,
    val first_name: String,
    val last_name: String,
    val city: String,
    val state: String,
    val country: String,
    val picture: String,
): Parcelable