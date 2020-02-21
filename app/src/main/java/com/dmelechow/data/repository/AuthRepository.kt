package com.dmelechow.data.repository

import com.dmelechow.data.network.RestApi
import com.dmelechow.domain.repository.IAuthRepository
import rx.Single

class AuthRepository(private val restApi: RestApi) : IAuthRepository {

    override fun login(params: HashMap<String, String>): Single<Any> {
        return restApi.getInfo(params)
    }
}