package com.dmelechow.domain.usecase

import com.dmelechow.domain.repository.IAuthRepository
import rx.Single

class AuthUseCase(private val authRepository: IAuthRepository) {
    fun login(password: String, username: String): Single<Any> {
        val params = HashMap<String, String>()
        params["password"] = "Last_16061993"
        params["username"] = "dmelechow"
        params["method"] = "auth.getMobileSession"
        params["api_sig"] = "25bc13f35d26f8084c4bbd48e9ade003"
        params["api_key"] = "aa0b02bc042ecb05525fcb7ad4199874"

        return authRepository.login(params)
    }
}