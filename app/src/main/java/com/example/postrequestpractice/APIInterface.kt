package com.example.postrequestpractice

import retrofit2.Call
import retrofit2.http.*


data class Data(val name:String , val location:String )
data class Data1(val id: Int , val name:String , val location:String )

interface APIInterface {
    @GET("/test/")
    fun doGetListResources(): Call<List<TestList?>>

    @POST("/test/")
    fun adduser(@Body userdata: Data ): Call<Data>

    @PUT("/test/{id}")
    fun updateUser(@Path("id") id: Int, @Body userdata: Data1): Call<Data1>

    @DELETE ("/test/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>
}