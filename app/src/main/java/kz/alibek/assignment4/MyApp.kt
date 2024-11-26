package kz.alibek.assignment4

import android.app.Application
import kz.alibek.assignment4.ex1.di.exercise1Module
import kz.alibek.assignment4.ex2.di.exercise2Module
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MyApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(
                listOf(
                    exercise1Module,
                    exercise2Module,
                )
            )
        }
    }
}