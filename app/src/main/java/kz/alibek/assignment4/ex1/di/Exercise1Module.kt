package kz.alibek.assignment4.ex1.di

import kz.alibek.assignment4.ex1.UserViewModel
import kz.alibek.assignment4.ex1.model.AppDatabase
import kz.alibek.assignment4.ex1.repository.UserRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exercise1Module = module {

    single {
        UserRepository(
            userDao = get<AppDatabase>().userDao()
        )
    }

    single<AppDatabase> {
        AppDatabase.getDatabase(androidContext())
    }

    viewModel {
        UserViewModel(
            repository = get(),
        )
    }

}