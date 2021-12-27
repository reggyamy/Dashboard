package com.reggya.dashboard.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "RestMethod", strict = false)
data class XMLBerita @JvmOverloads constructor(
    var generator: String? = null,

    var version: String? = null,

    @field: ElementList(name = "getBeritaPerHalaman", inline = true, required = false)
    var getBeritaPerHalaman: GetBeritaPerHalaman? = null

)

@Root(name = "getBeritaPerHalaman", strict = false)
data class GetBeritaPerHalaman (
    @field: ElementList(name = "response", inline = true, required = false, empty = true)
    var response: List<BeritaItem>? = null,

)

@Parcelize
@Root(name = "berita", strict = false)
data class BeritaItem @JvmOverloads constructor(
    @field: Element(name = "tanggal")
    var tanggal: String? = null,

    @field: Element(name = "kategori")
    var kategori: String? = null,

    @field: Element(name = "title")
    var title: String? = null,

    @field: Element(name = "isi")
    var isi: String? = null

):Parcelable