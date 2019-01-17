package com.example.piachimov.firebasekotlinexample.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (val id: String, val username: String, val profileImageUrl: String): Parcelable{
    constructor() : this("", "", "")
}