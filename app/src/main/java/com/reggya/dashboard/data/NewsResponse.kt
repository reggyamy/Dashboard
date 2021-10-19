package com.reggya.dashboard.data

import com.google.gson.annotations.SerializedName

data class Response(

	@field:SerializedName("berita")
	val berita: List<BeritaItem?>? = null
)

data class BeritaItem(

	@field:SerializedName("kategori")
	val kategori: String? = null,

	@field:SerializedName("tanggal")
	val tanggal: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("isi")
	val isi: String? = null
)
