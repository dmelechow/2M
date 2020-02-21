package com.dmelechow.presentation.auth

import androidx.lifecycle.MutableLiveData
import com.dmelechow.domain.usecase.AuthUseCase
import com.dmelechow.presentation.base.BaseViewModel
import rx.schedulers.Schedulers

class AuthViewModel(private val authUseCase: AuthUseCase) : BaseViewModel() {

    val authData = MutableLiveData<Any>()

    fun login(password: String, username: String) {
//        compositeDisposable.add(
//            authUseCase.login(password, username)
//                .subscribeOn(Schedulers.io())
//                .subscribe(
//                    { res -> authData.postValue(res) },
//                    { error -> authData.postValue(error) }
//                )
//        )
    }
}
