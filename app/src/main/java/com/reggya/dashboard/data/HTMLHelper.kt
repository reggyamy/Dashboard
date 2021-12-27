package com.reggya.dashboard.data

import com.reggya.dashboard.BuildConfig.NEWS_URL

object HTMLHelper{

    const val INDEX_EXTRACT_IMAGE = 0
    const val INDEX_EXTRACT_HTML = 1

    fun getString(html: String?): Array<String?> {
        var imageURL = ""
        var html = html

        if (html != null) {
            if (html.contains("<img")){
                if (html.contains("src=\"")){
                    imageURL = html.substring(html.indexOf("src=\"") + 5)
                    imageURL = imageURL.substring(0, imageURL.indexOf("\""))
                    imageURL = imageURL.replace("\\\\", "")
                    imageURL = imageURL.replace("\"", "")
                    imageURL = imageURL.replace("\'", "")
                    imageURL = imageURL.replace("&nbsp;", "")
                    imageURL = imageURL.replace(" ", "")
                    imageURL = imageURL.trim()
                    imageURL = NEWS_URL + imageURL

    //                html = html.replaceFirst("<(.*?)\\>", "");
                    html = html.replaceFirst("<img(.*?)\\>", "")
//                    content = content.replace("<p style=\"text-align:justify\"&nbsp;</p>", "")
//                    content.trim()
                } else {
                    html = html.replaceFirst("<img(.*?)\\>", "")
                }
            }
        }
        val result = arrayOfNulls<String>(2)
        result[INDEX_EXTRACT_IMAGE] = imageURL
        result[INDEX_EXTRACT_HTML] = html
        return result
    }

}
