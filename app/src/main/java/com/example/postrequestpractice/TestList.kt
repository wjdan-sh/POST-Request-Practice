package com.example.postrequestpractice

import com.google.gson.annotations.SerializedName

class TestList {

        @SerializedName("pk")
        var pk: Int? = null

        @SerializedName("name")
        var name: String? = null

        @SerializedName("location")
        var location: String? = null
}