package br.com.robsonribeiroft

import android.app.Application
import br.com.robsonribeiroft.androidnews.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(appModule)
        }.androidContext(this@AppApplication)
    }
}