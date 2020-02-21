package com.dmelechow.data.repository

import com.dmelechow.data.datasource.FileDataSource
import com.dmelechow.data.model.Album
import com.dmelechow.data.model.AlbumArtistResult
import com.dmelechow.data.network.RestApi
import com.dmelechow.domain.repository.IAlbumRepository
import io.reactivex.Completable
import io.reactivex.Observable

class AlbumRepository(
    private val restApi: RestApi,
    private val fileDataSource: FileDataSource
) : IAlbumRepository {

    override fun getAlbums(params: HashMap<String, String>): Observable<AlbumArtistResult> {
        return restApi.getAlbums(params)
    }

    override fun saveAlbum(album: Album): Completable = fileDataSource.saveAlbumToFile(album)

    override fun getSavedAlbums(): Observable<ArrayList<Album>> = fileDataSource.getAlbumsToFile()

    override fun removeSavedAlbum(album: Album): Completable = fileDataSource.removeSavedAlbum(album)
}