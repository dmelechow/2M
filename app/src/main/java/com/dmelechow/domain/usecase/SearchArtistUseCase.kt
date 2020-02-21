package com.dmelechow.domain.usecase

import com.dmelechow.data.model.SearchDataResult
import com.dmelechow.domain.repository.IArtistRepository
import com.dmelechow.utils.Constans
import io.reactivex.Observable

class SearchArtistUseCase(private val artistRepository: IArtistRepository) {

    fun search(artist: String): Observable<SearchDataResult> {
        val params = HashMap<String, String>()
        params["method"] = "artist.search"
        params["api_key"] = Constans.apiKey
        params["artist"] = artist
        params["format"] = "json"

        return artistRepository.search(params)
    }
}