package com.omidavz.animalsapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.omidavz.animalsapp.model.AnimalList
import com.omidavz.animalsapp.model.AnimalApiService
import com.omidavz.animalsapp.di.ApiComponent
import com.omidavz.animalsapp.model.di.DaggerApiComponent
import com.omidavz.animalsapp.model.di.DaggerViewModelComponent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel(application: Application) : AndroidViewModel(application) {
    val animals by lazy { MutableLiveData<AnimalList>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val allAnimals by lazy { MutableLiveData<AnimalList>() }

    private val disposable = CompositeDisposable()

    @Inject
    lateinit var apiService: AnimalApiService

    init {
        DaggerViewModelComponent.create().inject(this)
    }

    fun refresh(name: String) {
        loading.value = true
        if (animals.value != null) {
            getAnimals(name)
        } else {
            getAllAnimals()

        }
    }

    fun getAnimals(name: String) {
        loading.value = true
        disposable.add(
            apiService.getAnimals(name)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AnimalList>() {
                    override fun onSuccess(list: AnimalList) {
                        loadError.value = false
                        animals.value = list
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                        animals.value = null

                    }
                })
        )
    }

    fun getAllAnimals() {
        disposable.add(
            apiService.getAllAnimals()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<AnimalList>() {
                    override fun onSuccess(list: AnimalList) {
                        loadError.value = false
                        allAnimals.value = list
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        loading.value = false
                        loadError.value = true
                    }
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}