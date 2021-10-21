package com.example.melnyk_sportea_app.repository

import android.content.Context
import com.example.melnyk_sportea_app.data.source.local.LocalDataSourceImpl
import com.example.melnyk_sportea_app.data.source.remote.RemoteDataSourceImpl
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.wrapper.Quotes
import io.reactivex.Observable

class QuotesRepository(
    private val remoteDataSourceImpl: RemoteDataSourceImpl,
    private val localDataSourceImpl: LocalDataSourceImpl,
    private val context: Context
) {

//    init {
//        createJobScheduler()
//    }

    fun getQuotesListFromApi(): Observable<Quotes> {
        return remoteDataSourceImpl.getQuotes()
    }

    fun getQuotesListFromDB(): List<Quote> {
        return localDataSourceImpl.getQuotes()
    }

    suspend fun addQuote(quote: Quote) {
        localDataSourceImpl.addQuote(quote)
    }

    suspend fun removeQuote(quote: Quote) {
        localDataSourceImpl.removeQuote(quote)
    }

//    private fun createJobScheduler() {
//        val componentName = ComponentName(context, PeriodicRequest::class.java)
//        val builder = JobInfo.Builder(11, componentName)
//            .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
//            .setRequiresCharging(true)
//            .build()
//        val jobScheduler = context.getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
//        jobScheduler.schedule(builder)
//    }
}