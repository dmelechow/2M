package com.dmelechow.domain.repository

import com.dmelechow.data.model.SearchDataResult
import io.reactivex.Observable


interface IArtistRepository {
    fun search(params: HashMap<String, String>): Observable<SearchDataResult>
}