package com.example.melnyk_sportea_app.db.firebase

import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference

class CloudStorageHelper {
    companion object {
        private var instance: StorageReference? = null
        val STORAGE_KEY = "images"

        fun getCloudStorageInstance(): StorageReference {
            if (instance == null) {
                instance = FirebaseStorage.getInstance().getReference(STORAGE_KEY)
            }
            return instance!!
        }
    }
}