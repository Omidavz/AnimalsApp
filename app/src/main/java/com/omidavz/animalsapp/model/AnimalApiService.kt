package com.omidavz.animalsapp.model

import com.omidavz.animalsapp.model.di.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class AnimalApiService {

    @Inject
    lateinit var api: AnimalApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getAnimals(name: String): Single<AnimalList> {
        return api.getAnimals(name)
    }

    fun getAllAnimals(): Single<AnimalList> {
        return api.getAllAnimals()
    }
}