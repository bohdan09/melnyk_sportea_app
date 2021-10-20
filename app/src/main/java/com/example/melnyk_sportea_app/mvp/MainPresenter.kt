package com.example.melnyk_sportea_app.mvp

import android.util.Log
import com.example.melnyk_sportea_app.api.ApiService
import com.example.melnyk_sportea_app.api.RetrofitImpl
import com.example.melnyk_sportea_app.model.wrapper.Quotes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter(
    val mvi: MainViewInterface
) : MainPresenterInterface {

    override fun getQuotes() {
        getObservable()?.subscribeWith(getObserver())
    }

    fun getObservable(): Observable<Quotes>? {
        return RetrofitImpl.getRetrofit().create(ApiService::class.java)
            .getQuotesList().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    fun getObserver(): DisposableObserver<Quotes> {
        return object : DisposableObserver<Quotes>() {
            override fun onNext(t: Quotes) {
                mvi.displayQuotes(t)
            }

            override fun onError(e: Throwable) {
                mvi.displayError("error")
            }

            override fun onComplete() {
                Log.d("TAG", "Completed")
            }
        }
    }
}