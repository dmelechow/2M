package com.dmelechow.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

enum class SizeImage(val title: String) : Serializable {
    @SerializedName("small")
    SMALL("small"),

    @SerializedName("medium")
    MEDIUM("medium"),

    @SerializedName("large")
    LARGE("large"),

    @SerializedName("extralarge")
    EXTRALARGE("extralarge"),

    @SerializedName("mega")
    MEGA("mega")
}