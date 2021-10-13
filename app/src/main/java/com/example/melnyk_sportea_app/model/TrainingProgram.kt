package com.example.melnyk_sportea_app.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.melnyk_sportea_app.db.room.Constants.TRAINING_PROGRAM_TABLE_NAME
import com.example.melnyk_sportea_app.utils.ProgramLevel

@Entity(tableName = TRAINING_PROGRAM_TABLE_NAME)
data class TrainingProgram(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val programName: String? = null,
    val imageUrl: String? = null,
    var level: ProgramLevel? = null,
    val exercises: List<Exercise>? = null,
    val updateVersion: Int? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        ProgramLevel.valueOf(parcel.readString()!!),
        parcel.createTypedArrayList(Exercise),
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(programName)
        parcel.writeString(imageUrl)
        parcel.writeTypedList(exercises)
        parcel.writeValue(updateVersion)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TrainingProgram> {
        override fun createFromParcel(parcel: Parcel): TrainingProgram {
            return TrainingProgram(parcel)
        }

        override fun newArray(size: Int): Array<TrainingProgram?> {
            return arrayOfNulls(size)
        }
    }
}
