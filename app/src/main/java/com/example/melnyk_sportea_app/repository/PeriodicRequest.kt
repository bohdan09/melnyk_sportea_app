package com.example.melnyk_sportea_app.repository

import android.app.job.JobParameters
import android.app.job.JobService
import com.example.melnyk_sportea_app.App
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.Quote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PeriodicRequest : JobService() {
    @Inject
    lateinit var localDataSourceImpl: LocalDataSourceImpl

    @Inject
    lateinit var remoteDataSourceImpl: RemoteDataSourceImpl
    override fun onStartJob(p0: JobParameters?): Boolean {
        (this.applicationContext as App).getAppComponent().inject(this)

        CoroutineScope(Dispatchers.IO).launch {
            val list = getQuotes()
            addQuotesToDB(list)
        }
        return true
    }

    override fun onStopJob(p0: JobParameters?): Boolean {
        return true
    }

    private suspend fun getQuotes(): List<Quote> {
        return remoteDataSourceImpl.getQuotes()
    }

    private suspend fun addQuotesToDB(list: List<Quote>) {
        for (i in list.indices) {
            localDataSourceImpl.addQuote(list[i])
        }
    }
}