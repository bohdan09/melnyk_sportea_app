package com.example.melnyk_sportea_app.model

import android.os.Parcel
import android.os.Parcelable
import com.example.melnyk_sportea_app.utils.Difficulty
import com.example.melnyk_sportea_app.utils.MuscleGroup
import java.net.URL

data class Exercise(
    val id: Int? = null,
    val name: String? = null,
    val muscleGroup: MuscleGroup? = null,
    val imageUrl: String? = null, //
    val description: String? = null,
    val repeats: Int? = null,
    val sets: Int? = null,
    val weight: Float? = null,
    val workTime: Long? = null,
    val restTime: Long? = null,
    val dayNum: Int? = null,
    val kcal: Int? = null,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        MuscleGroup.valueOf(parcel.readString()!!),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Float::class.java.classLoader) as? Float,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeValue(repeats)
        parcel.writeValue(sets)
        parcel.writeValue(weight)
        parcel.writeValue(workTime)
        parcel.writeValue(restTime)
        parcel.writeValue(dayNum)
        parcel.writeValue(kcal)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Exercise> {
        override fun createFromParcel(parcel: Parcel): Exercise {
            return Exercise(parcel)
        }

        override fun newArray(size: Int): Array<Exercise?> {
            return arrayOfNulls(size)
        }
    }
}
