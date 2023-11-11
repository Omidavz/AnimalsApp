package com.omidavz.animalsapp.di

import androidx.lifecycle.ViewModel
import com.omidavz.animalsapp.model.AnimalApiService
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: AnimalApiService)


}