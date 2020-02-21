package com.dmelechow.domain.repository

import rx.Single


interface IAuthRepository {
    fun login(params: HashMap<String, String>): Single<Any>
}