package com.dmelechow.data.model

import com.google.gson.annotations.SerializedName

class SearchDataResult {
    var results: SearchResult? = null
}

class SearchResult {
    @SerializedName("opensearch:totalResults")
    var totalResults: String? = null

    var artistmatches: Artistmatches? = null
}

class Artistmatches {
    var artist: List<Artist>? = null
}

