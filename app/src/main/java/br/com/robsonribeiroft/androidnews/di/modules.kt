package br.com.robsonribeiroft.androidnews.di

import br.com.robsonribeiroft.androidnews.AppViewModel
import br.com.robsonribeiroft.androidnews.data.repository.AppRepository
import br.com.robsonribeiroft.androidnews.data.service.ApiService
import br.com.robsonribeiroft.androidnews.data.service.RetrofitClient
import br.com.robsonribeiroft.androidnews.domain.GetFeedNewsByProductPaginatedUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        AppViewModel(
            getFeedNewsPagination = get()
        )
    }
    single <ApiService> { RetrofitClient.createInstance() }
    single { AppRepository(apiService = get()) }
    single { GetFeedNewsByProductPaginatedUseCase(repository = get()) }
}