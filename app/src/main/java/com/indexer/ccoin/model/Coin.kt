package com.indexer.ccoin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "coin")
class Coin() : Parcelable {
    @PrimaryKey
    @NonNull
    @SerializedName("Id")
    var coinId: String = ""
    @SerializedName("ImageUrl")
    @Expose
    var imageUrl: String? = ""
    @Expose
    @SerializedName("Algorithm")
    var algorithm: String? = ""
    @Expose
    @SerializedName("ProofType")
    var proofType: String? = ""

    constructor(parcel: Parcel) : this() {
        coinId = parcel.readString()
        imageUrl = parcel.readString()
        algorithm = parcel.readString()
        proofType = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(coinId)
        parcel.writeString(imageUrl)
        parcel.writeString(algorithm)
        parcel.writeString(proofType)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coin> {
        override fun createFromParcel(parcel: Parcel): Coin {
            return Coin(parcel)
        }

        override fun newArray(size: Int): Array<Coin?> {
            return arrayOfNulls(size)
        }
    }
}
