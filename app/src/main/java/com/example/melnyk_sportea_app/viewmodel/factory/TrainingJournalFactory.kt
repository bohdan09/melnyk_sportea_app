package com.example.melnyk_sportea_app.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.melnyk_sportea_app.viewmodel.FinishFragmentViewModel
import javax.inject.Inject
import javax.inject.Provider

class TrainingJournalFactory @Inject constructor(myViewModelProvider: Provider<FinishFragmentViewModel>) :
    ViewModelProvider.Factory {

    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        FinishFragmentViewModel::class.java to myViewModelProvider
    )

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}