package br.com.robsonribeiroft.androidnews.di

import br.com.robsonribeiroft.androidnews.AppViewModel
import br.com.robsonribeiroft.androidnews.data.repository.AppRepository
import br.com.robsonribeiroft.androidnews.data.service.ApiService
import br.com.robsonribeiroft.androidnews.data.service.RetrofitClient
import br.com.robsonribeiroft.androidnews.domain.GetFeedNewsUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AppViewModel(getFeedNewsUseCase = get()) }
    single <ApiService> { RetrofitClient.createInstance() }
    single { AppRepository(apiService = get()) }
    single { GetFeedNewsUseCase(repository = get()) }
}