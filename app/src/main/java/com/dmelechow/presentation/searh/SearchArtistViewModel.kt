package com.dmelechow.presentation.searh

import androidx.lifecycle.MutableLiveData
import com.dmelechow.data.model.Resource
import com.dmelechow.data.model.SearchResult
import com.dmelechow.domain.usecase.SearchArtistUseCase
import com.dmelechow.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class SearchArtistViewModel(private val artistUseCase: SearchArtistUseCase) : BaseViewModel() {

    val searchState = MutableLiveData<Resource<SearchResult>>()

    fun search(artist: String) {
        compositeDisposable.add(
            artistUseCase.search(artist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    if (res.results?.artistmatches?.artist != null) {
                        if(res.results?.artistmatches?.artist!!.isNotEmpty()) {
                            searchState.postValue(Resource.Success(res.results!!))
                        } else {
//                            searchState.postValue(Resource.Error())
                        }
                    } else {
//                        searchState.postValue(Resource.Error())
                    }
                }, { error ->
//                    searchState.postValue(Resource.Error(message = error.message))
                })
        )
    }
}
