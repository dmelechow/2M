package com.dmelechow.data.repository

import com.dmelechow.data.datasource.FileDataSource
import com.dmelechow.data.model.Album
import com.dmelechow.data.model.AlbumArtistResult
import com.dmelechow.data.model.SearchDataResult
import com.dmelechow.data.network.RestApi
import com.dmelechow.domain.repository.IArtistRepository
import io.reactivex.Completable
import io.reactivex.Observable

class ArtistRepository(
    private val restApi: RestApi
) : IArtistRepository {
    override fun search(params: HashMap<String, String>): Observable<SearchDataResult> {
        return restApi.search(params)
    }
}

