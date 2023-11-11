package com.omidavz.animalsapp.di

import androidx.lifecycle.ViewModel
import com.omidavz.animalsapp.viewmodel.ListViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: ListViewModel)
}