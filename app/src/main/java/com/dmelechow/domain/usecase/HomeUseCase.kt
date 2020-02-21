package com.dmelechow.domain.usecase

import com.dmelechow.data.model.Album
import com.dmelechow.domain.repository.IAlbumRepository
import io.reactivex.Completable
import io.reactivex.Observable

class HomeUseCase(private val albumRepository: IAlbumRepository) {

    fun getSavedAlbums(): Observable<ArrayList<Album>> = albumRepository.getSavedAlbums()

    fun removeSavedAlbum(album: Album): Completable = albumRepository.removeSavedAlbum(album)
}