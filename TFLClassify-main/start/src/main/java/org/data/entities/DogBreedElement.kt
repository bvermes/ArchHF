package org.data.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dogbreedelement")
data class DogBreedElement(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "detected") var detected: Boolean,
    @ColumnInfo(name = "description") var description: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString().toString(),
        parcel.readInt() == 1,
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(name)
        parcel.writeInt(if (detected) 1 else 0)
        parcel.writeString(description)

    }

    override fun describeContents(): Int {
        return 0
    }


    companion object CREATOR : Parcelable.Creator<DogBreedElement> {
        override fun createFromParcel(parcel: Parcel): DogBreedElement {
            return DogBreedElement(parcel)
        }

        override fun newArray(size: Int): Array<DogBreedElement?> {
            return arrayOfNulls(size)
        }
    }
}