package br.com.mrocigno.githubapi.model

import android.os.Parcel
import android.os.Parcelable

data class GithubOwner(
    val id: Int,
    val login: String?,
    val avatar_url: String?,
    val url: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(login)
        parcel.writeString(avatar_url)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<GithubOwner> {
        override fun createFromParcel(parcel: Parcel): GithubOwner {
            return GithubOwner(parcel)
        }

        override fun newArray(size: Int): Array<GithubOwner?> {
            return arrayOfNulls(size)
        }
    }
}