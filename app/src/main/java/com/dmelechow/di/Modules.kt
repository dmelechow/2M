package com.dmelechow.di

import com.dmelechow.BuildConfig
import com.dmelechow.data.datasource.FileDataSource
import com.dmelechow.data.network.RestApi
import com.dmelechow.data.network.createNetworkClient
import com.dmelechow.data.repository.AlbumRepository
import com.dmelechow.data.repository.ArtistRepository
import com.dmelechow.data.repository.AuthRepository
import com.dmelechow.domain.repository.IAlbumRepository
import com.dmelechow.domain.repository.IArtistRepository
import com.dmelechow.domain.repository.IAuthRepository
import com.dmelechow.domain.usecase.AlbumArtistUseCase
import com.dmelechow.domain.usecase.AuthUseCase
import com.dmelechow.domain.usecase.HomeUseCase
import com.dmelechow.domain.usecase.SearchArtistUseCase
import com.dmelechow.presentation.albumartist.AlbumArtistViewModel
import com.dmelechow.presentation.auth.AuthViewModel
import com.dmelechow.presentation.home.HomeViewModel
import com.dmelechow.presentation.searh.SearchArtistViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit

fun injectModules() = loadModules

private val loadModules by lazy {
    loadKoinModules(
        viewModelModule,
        useCaseModule,
        repositoryModule,
        dataSourceModule,
        networkModule
    )
}

val viewModelModule: Module = module {
    viewModel { AuthViewModel(authUseCase = get()) }
    viewModel { SearchArtistViewModel(artistUseCase = get()) }
    viewModel { AlbumArtistViewModel(artistUseCase = get()) }
    viewModel { HomeViewModel(useCase = get()) }
}

val useCaseModule: Module = module {
    factory { SearchArtistUseCase(artistRepository = get()) }
    factory { AuthUseCase(authRepository = get()) }
    factory { AlbumArtistUseCase(albumRepository = get()) }
    factory { HomeUseCase(albumRepository = get()) }
}

val repositoryModule: Module = module {
    single { ArtistRepository(restApi = get()) as IArtistRepository }
    single { AuthRepository(restApi = get()) as IAuthRepository }
    single { AlbumRepository(restApi = get(), fileDataSource = get()) as IAlbumRepository }
}

val dataSourceModule: Module = module {
    single { FileDataSource(androidContext()) }
}

val networkModule: Module = module {
    single { restApi }
}

//val fileSystemModule: Module = module {
//    single { FileDataSource(androidContext()) }
//}


const val BASE_URL = "https://ws.audioscrobbler.com/"

private val retrofit: Retrofit = createNetworkClient(BASE_URL, BuildConfig.DEBUG)

private val restApi: RestApi = retrofit.create(RestApi::class.java)

//private val fileDataSource = FileDataSource()





