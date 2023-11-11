package com.omidavz.animalsapp.model

import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PUT
import retrofit2.http.Query

interface AnimalApi {

    @Headers("x-api-key: q2BnKNReyeyG4vyzGjt9zg==qTFGaXhzTr9xgFO3")
    @GET("animals")
    fun getAnimals(@Query("name") name: String): Single<AnimalList>

    @Headers("x-api-key: q2BnKNReyeyG4vyzGjt9zg==qTFGaXhzTr9xgFO3")
    @GET("animals")
    fun getAllAnimals(@Query("name") name: String = " "): Single<AnimalList>


}