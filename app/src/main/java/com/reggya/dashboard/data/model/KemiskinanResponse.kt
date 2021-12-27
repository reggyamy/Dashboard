package com.reggya.dashboard.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KemiskinanResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
) : Parcelable

@Parcelize
data class DataItem(

	@field:SerializedName("Jumlah Bansos")
	val jumlahBansos: Double? = null,

	@field:SerializedName("Jumlah Pengangguran")
	val jumlahPengangguran: Int? = null,

	@field:SerializedName("Provinsi")
	val provinsi: String? = null,

	@field:SerializedName("Jumlah Penduduk Miskin")
	val jumlahPendudukMiskin: Double? = null
) : Parcelable
