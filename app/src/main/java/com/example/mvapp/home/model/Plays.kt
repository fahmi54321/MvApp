package com.example.mvapp.home.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Plays(
    var nama:String?="",
    var url:String?=""
):Parcelable