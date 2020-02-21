package com.dmelechow.data.network

import com.dmelechow.data.model.AlbumArtistResult
import com.dmelechow.data.model.SearchDataResult
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface RestApi {
    @POST("2.0/")
    fun getInfo(@QueryMap params: HashMap<String, String>): rx.Single<Any>

    @POST("2.0/")
    fun search(@QueryMap params: HashMap<String, String>): Observable<SearchDataResult>

    @POST("2.0/")
    fun getAlbums(@QueryMap params: HashMap<String, String>): Observable<AlbumArtistResult>
}
