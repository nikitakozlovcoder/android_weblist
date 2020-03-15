package com.example.myapplication

import com.google.gson.annotations.SerializedName


data class Record(
    @SerializedName(value = "name")
    var Name:String,
    @SerializedName(value = "url")
    var Url:String
)