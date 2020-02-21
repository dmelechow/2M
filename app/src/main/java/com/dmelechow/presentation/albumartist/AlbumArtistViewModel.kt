package com.dmelechow.presentation.albumartist

import androidx.lifecycle.MutableLiveData
import com.dmelechow.data.model.Album
import com.dmelechow.data.model.AlbumArtist
import com.dmelechow.data.model.Resource
import com.dmelechow.domain.usecase.AlbumArtistUseCase
import com.dmelechow.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlbumArtistViewModel(private val artistUseCase: AlbumArtistUseCase) : BaseViewModel() {
    val dataState = MutableLiveData<Resource<AlbumArtist>>()

    fun savedAlbum(album: Album) {
        compositeDisposable.add(
            artistUseCase.saveAlbum(album)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // :TODO use single event data
                }, {
                    // :TODO handle this error
                })
        )
    }

    fun getAlbums(mbid: String) {
        compositeDisposable.add(
            artistUseCase.getAlbums(mbid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ res ->
                    if (res.topalbums?.album != null) {
                        if (res.topalbums?.album!!.isNotEmpty()) {
                            dataState.postValue(Resource.Success(res.topalbums!!))
                        } else {
                            // :TODO handle this error
                        }
                    } else {
                        // :TODO handle this error
                    }
                }, { error ->
                    // :TODO handle this error
                })
        )
    }
}
