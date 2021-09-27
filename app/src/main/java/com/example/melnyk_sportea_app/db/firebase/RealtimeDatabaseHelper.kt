package com.example.melnyk_sportea_app.db.firebase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RealtimeDatabaseHelper {
    companion object {
        private var databaseInstance: DatabaseReference? = null
        const val REFERENCE_KEY = "programs"

        fun getRealtimeDatabaseInstance(): DatabaseReference {
            if (databaseInstance == null) {
                databaseInstance = FirebaseDatabase.getInstance().getReference(REFERENCE_KEY)
            }
            return databaseInstance!!
        }
    }
}