package com.example.myapplication

import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


const val BASE_URL="https://cdn-api.co-vin.in"
interface SetuInterface {

    @GET("/api/v2/appointment/sessions/public/findByPin")
    fun getlist(@Query("pincode")pincode:String,@Query("date")datee:String):Call<MyData>

}

object SetuService{



    val setuInterface:SetuInterface
    init {
//        var gson = GsonBuilder()
//            .setLenient()
//            .create()
        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        setuInterface=retrofit.create(SetuInterface::class.java)
    }


}