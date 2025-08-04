package br.com.robsonribeiroft.androidnews.di

import br.com.robsonribeiroft.androidnews.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { AppViewModel() }
}