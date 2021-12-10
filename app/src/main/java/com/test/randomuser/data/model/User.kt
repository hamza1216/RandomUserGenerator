package com.test.randomuser.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User (
    val gender: String,
    val first_name: String,
    val last_name: String,
    val location: String,
    val picture: String,
): Parcelable