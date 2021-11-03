package com.reggya.dashboard.data

import android.net.Uri
import androidx.test.services.storage.internal.TestStorageUtil.getInputStream
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import java.io.IOException
import java.io.InputStream
import java.net.URI
import java.net.URL

class XMLHandler {

    private val berita = ArrayList<BeritaItem>()
    private var berita_ : BeritaItem? = null
    private var text_ : String? = null

    fun parseXML(inputStream: InputStream): List<BeritaItem>{
        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val parser = factory.newPullParser()
            parser.setInput(inputStream, null)
            var eventType = parser.eventType
            while (eventType != XmlPullParser.END_DOCUMENT){
                val tagName = parser.name
                when(eventType){
                    XmlPullParser.START_TAG -> if (tagName.equals("berita", ignoreCase = true)){
                        berita_ = BeritaItem()
                    }
                    XmlPullParser.TEXT -> text_ = parser.text
                    XmlPullParser.END_TAG ->
                        if (tagName.equals("berita", ignoreCase = true)){
                            berita_?.let { beritaItem ->
                                berita.add(beritaItem) }
                        }
                        else if (tagName.equals("tanggal", ignoreCase = true)){
                            berita_?.tanggal = text_
                        }
                        else if (tagName.equals("kategori", ignoreCase = true)){
                            berita_?.kategori = text_
                        }
                        else if (tagName.equals("gambar", ignoreCase = true)){
                            berita_?.gambar = text_
                        }
                        else if (tagName.equals("title", ignoreCase = true)){
                            berita_?.title = text_
                        }
                        else if (tagName.equals("isi", ignoreCase = true)){
                            berita_?.isi = text_
                        }
                        else eventType = parser.next()
                }
            }
        }catch (e: XmlPullParserException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }
        return berita
    }

}
