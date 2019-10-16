package br.com.mrocigno.githubapi.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Github(
    val placeholder : Boolean = false,
    val infoCard : Boolean = false,
    val name : String? = null,
    val full_name : String? = null,
    val owner : GithubOwner? = null,
    val html_url : String? = null,
    val description : String? = null,
    val forks_count : Int? = null,
    val stargazers_count : Int? = null
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readByte() != 0.toByte(),
        parcel.readByte() != 0.toByte(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(GithubOwner::class.java.classLoader),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeByte(if (placeholder) 1 else 0)
        parcel.writeByte(if (infoCard) 1 else 0)
        parcel.writeString(name)
        parcel.writeString(full_name)
        parcel.writeParcelable(owner, flags)
        parcel.writeString(html_url)
        parcel.writeString(description)
        parcel.writeValue(forks_count)
        parcel.writeValue(stargazers_count)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Github> {
        override fun createFromParcel(parcel: Parcel): Github {
            return Github(parcel)
        }

        override fun newArray(size: Int): Array<Github?> {
            return arrayOfNulls(size)
        }
    }

}