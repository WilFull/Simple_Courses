package com.example.simplecourses.homeView.view

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Courses(var titleImage : Int,
                   var title_heading : String,
                   var title_subheading : String) : Parcelable, Serializable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(titleImage)
        parcel.writeString(title_heading)
        parcel.writeString(title_subheading)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Courses> {
        override fun createFromParcel(parcel: Parcel): Courses {
            return Courses(parcel)
        }

        override fun newArray(size: Int): Array<Courses?> {
            return arrayOfNulls(size)
        }
    }
}
