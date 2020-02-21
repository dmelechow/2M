package com.dmelechow.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Image : Serializable {

    var size: SizeImage? = null

    @SerializedName("#text")
    var text: String? = null
}