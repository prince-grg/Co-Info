package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Session(

    val address: String,
    val available_capacity: Int,
    val date: String,
    val fee: String,
    val fee_type: String,
    val min_age_limit: Int,
    @SerializedName("name")
    val name: String,
    val pincode: Int,
    val vaccine: String
)