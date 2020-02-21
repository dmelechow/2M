package com.dmelechow.presentation.home

import androidx.lifecycle.MutableLiveData
import com.dmelechow.data.model.Album
import com.dmelechow.data.model.Resource
import com.dmelechow.domain.usecase.HomeUseCase
import com.dmelechow.presentation.base.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val useCase: HomeUseCase) : BaseViewModel() {

    val dataState = MutableLiveData<Resource<ArrayList<Album>>>()

    fun getSavedAlbums() {
        compositeDisposable.add(
            useCase.getSavedAlbums()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it != null){
                        dataState.postValue(Resource.Success(it))
                    } else {
                        //: TODO handle this
                    }
                }, {
                    //: TODO handle this
                })
        )
    }

    fun removeSavedAlbum(album: Album) {
        compositeDisposable.add(
            useCase.removeSavedAlbum(album)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    // TODO handle
                }, {
                    //: TODO handle this
                })
        )
    }
}
