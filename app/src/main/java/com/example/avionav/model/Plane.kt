package com.example.avionav.model

import android.os.Parcel
import android.os.Parcelable

/**
 * This class will represent the kotlin object of planeInfo children in the database
 */
data class Plane(
    var id: String? = "",
    var name: String? = "",
    var description: String? = "",
    var imageURl: String? = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(imageURl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Plane> {
        override fun createFromParcel(parcel: Parcel): Plane {
            return Plane(parcel)
        }

        override fun newArray(size: Int): Array<Plane?> {
            return arrayOfNulls(size)
        }
    }
}
