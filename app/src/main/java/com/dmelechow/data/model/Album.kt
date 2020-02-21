package com.dmelechow.data.model

import java.io.Serializable

class Album : Serializable {
    var artist: Artist? = null

    var name: String? = null

    var playcount: Int? = null

    var mbid: String? = null

    var url: String? = null

    var image: List<Image>? = null
}