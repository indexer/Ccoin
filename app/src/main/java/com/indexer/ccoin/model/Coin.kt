package com.indexer.ccoin.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import android.support.v7.recyclerview.extensions.DiffCallback


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
    @SerializedName("CoinName")
    var coinName: String? = ""
    @Expose
    @SerializedName("ProofType")
    var proofType: String? = ""
    @Expose
    @SerializedName("TotalCoinSupply")
    var totalCoinSupply: String? = ""
    @SerializedName("SortOrder")
    var SortOrder: String? = ""
    @SerializedName("Url")
    var Url: String = ""


    constructor(parcel: Parcel) : this() {
        coinId = parcel.readString()
        imageUrl = parcel.readString()
        algorithm = parcel.readString()
        coinName = parcel.readString()
        proofType = parcel.readString()
        totalCoinSupply = parcel.readString()
        SortOrder = parcel.readString()
        Url = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(coinId)
        parcel.writeString(imageUrl)
        parcel.writeString(algorithm)
        parcel.writeString(coinName)
        parcel.writeString(proofType)
        parcel.writeString(totalCoinSupply)
        parcel.writeString(SortOrder)
        parcel.writeString(Url)
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

        val DIFF_CALLBACK: DiffCallback<Coin> = object : DiffCallback<Coin>() {
            override fun areItemsTheSame(oldCoin: Coin, newCoin: Coin): Boolean {
                return oldCoin.coinId === newCoin.coinId
            }

            override fun areContentsTheSame(oldCoin: Coin, newCoin: Coin): Boolean {
                return oldCoin.coinId == newCoin.coinId
            }
        }
    }


}
