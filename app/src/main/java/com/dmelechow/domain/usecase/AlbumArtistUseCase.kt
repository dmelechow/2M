package com.dmelechow.domain.usecase

import com.dmelechow.data.model.Album
import com.dmelechow.data.model.AlbumArtistResult
import com.dmelechow.domain.repository.IAlbumRepository
import com.dmelechow.domain.repository.IArtistRepository
import io.reactivex.Completable
import io.reactivex.Observable

class AlbumArtistUseCase(
    private val albumRepository: IAlbumRepository
) {

    fun getAlbums(mbid: String): Observable<AlbumArtistResult> {
        val params = HashMap<String, String>()
        params["method"] = "artist.getTopAlbums"
        params["api_key"] = "aa0b02bc042ecb05525fcb7ad4199874"
        params["mbid"] = mbid
        params["format"] = "json"

        return albumRepository.getAlbums(params)
    }

    fun getSavedAlbums() = albumRepository.getSavedAlbums()

    fun saveAlbum(album: Album): Completable = albumRepository.saveAlbum(album)
}