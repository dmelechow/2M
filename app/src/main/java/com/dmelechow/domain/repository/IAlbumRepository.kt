package com.dmelechow.domain.repository

import com.dmelechow.data.model.Album
import com.dmelechow.data.model.AlbumArtistResult
import io.reactivex.Completable
import io.reactivex.Observable


interface IAlbumRepository {

    fun getAlbums(params: HashMap<String, String>): Observable<AlbumArtistResult>

    fun saveAlbum(album: Album): Completable

    fun getSavedAlbums(): Observable<ArrayList<Album>>

    fun removeSavedAlbum(album: Album): Completable
}