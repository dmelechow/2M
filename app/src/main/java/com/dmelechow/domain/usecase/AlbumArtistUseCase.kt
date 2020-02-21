package com.dmelechow.domain.usecase

import com.dmelechow.data.model.Album
import com.dmelechow.data.model.AlbumArtistResult
import com.dmelechow.domain.repository.IAlbumRepository
import com.dmelechow.domain.repository.IArtistRepository
import com.dmelechow.utils.Constans
import io.reactivex.Completable
import io.reactivex.Observable

class AlbumArtistUseCase(
    private val albumRepository: IAlbumRepository
) {

    fun getAlbums(mbid: String): Observable<AlbumArtistResult> {
        val params = HashMap<String, String>()
        params["method"] = "artist.getTopAlbums"
        params["api_key"] = Constans.apiKey
        params["mbid"] = mbid
        params["format"] = "json"

        return albumRepository.getAlbums(params)
    }

    fun saveAlbum(album: Album): Completable = albumRepository.saveAlbum(album)
}