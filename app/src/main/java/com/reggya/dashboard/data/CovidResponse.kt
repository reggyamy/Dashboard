package com.reggya.dashboard

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class CovidResponse(

	@field:SerializedName("CovidResponse")
	val covidResponse: List<CovidResponseItem>
)

data class CovidResponseItem(

	@field:SerializedName("attributes")
	val attributes: Attributes
)

@Parcelize
data class Attributes(

	@field:SerializedName("FID")
	val id: Int? = null,

	@field:SerializedName("Kode_Provi")
	val kodeProvinsi: Int? = null,

	@field:SerializedName("Kasus_Meni")
	val kasusMeninggal: Int? = null,

	@field:SerializedName("Kasus_Posi")
	val kasusPositif: Int? = null,

	@field:SerializedName("Provinsi")
	val provinsi: String? = null,

	@field:SerializedName("Kasus_Semb")
	val kasusSembub: Int? = null
) : Parcelable
