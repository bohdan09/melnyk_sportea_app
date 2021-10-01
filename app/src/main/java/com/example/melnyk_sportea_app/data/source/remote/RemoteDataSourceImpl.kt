package com.example.melnyk_sportea_app.data.source.remote

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.melnyk_sportea_app.api.ApiService
import com.example.melnyk_sportea_app.data.source.RemoteDataSource
import com.example.melnyk_sportea_app.model.Quote
import com.example.melnyk_sportea_app.model.TrainingProgram
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private val dbReference: DatabaseReference,
) : RemoteDataSource {
    override suspend fun getQuotes(): List<Quote> {
        return apiService.getQuotesList()
    }

    override fun getTrainingProgramList(): LiveData<List<TrainingProgram>> {
        val trainingProgramList = mutableListOf<TrainingProgram>()
        val mutableLiveData = MutableLiveData<List<TrainingProgram>>()
        val liveData: LiveData<List<TrainingProgram>> = mutableLiveData

        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (i in snapshot.children) {
                    trainingProgramList.add(snapshot.getValue(TrainingProgram::class.java)!!)
                }
                mutableLiveData.value = trainingProgramList
            }

            override fun onCancelled(error: DatabaseError) {
            }
        }

        dbReference.addValueEventListener(valueEventListener)
        return liveData
    }

}