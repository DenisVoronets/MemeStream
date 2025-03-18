package com.testapp.memestream.di


import com.testapp.memestream.models.HomeScreenViewModel
import com.testapp.memestream.network.apiRequests.PostApiRequests
import com.testapp.memestream.network.apiRequests.UserApiRequests
import com.testapp.memestream.network.httpClient
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { httpClient() }
    single { PostApiRequests(get()) }
    single { UserApiRequests(get()) }
    //ViewModels
    viewModel { HomeScreenViewModel(get(), get()) }

}

fun initializeKoin() {
    startKoin {
        modules(appModule)
    }
}