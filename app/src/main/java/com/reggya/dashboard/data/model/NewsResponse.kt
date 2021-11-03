package com.reggya.dashboard.data

import android.os.Parcel
import android.os.Parcelable
import android.os.Parcelable.Creator
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


data class NewsResponse(

	@field:SerializedName("berita")
	val berita: List<ModelBeritaItem?>? = null

)

@Parcelize
data class ModelBeritaItem(

	@field:SerializedName("kategori")
	var kategori: String? = null,

	@field:SerializedName("tanggal")
	var tanggal: String? = null,

	@field:SerializedName("title")
	var title: String? = null,

	@field:SerializedName("gambar")
	var gambar: String? = null,

	@field:SerializedName("isi")
	var isi: String? = null
):Parcelable
