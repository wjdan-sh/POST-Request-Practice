package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


data class Data(val name:String , val location:String )

interface APIInterface {
    @GET("/test/")
    fun doGetListResources(): Call<List<TestList?>>

    @POST("/test/")
    fun adduser(@Body userdata: Data ): Call<Data>
}