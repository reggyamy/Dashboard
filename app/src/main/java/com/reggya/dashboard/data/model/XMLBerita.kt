package com.reggya.dashboard.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "RestMethod", strict = false)
data class XMLBerita @JvmOverloads constructor(
    var generator: String? = null,

    var version: String? = null,

    @field: ElementList(name = "get5BeritaFoto", inline = true, required = false)
    var get5BeritaFoto: Get5BeritaFoto? = null

)

@Root(name = "get5BeritaFoto", strict = false)
data class Get5BeritaFoto (
    @field: ElementList(name = "response", inline = true, required = false)
    var response: List<BeritaItem>? = null,

    @Element(name = "status")
    var status: String? = null
)

@Root(name = "berita", strict = false)
data class BeritaItem @JvmOverloads constructor(
    @field: Element(name = "tanggal")
    var tanggal: String? = null,

    @field: Element(name = "kategori")
    var kategori: String? = null,

    @field: Element(name = "gambar")
    var gambar: String? = null,

    @field: Element(name = "title")
    var title: String? = null,

    @field: Element(name = "isi")
    var isi: String? = null

)