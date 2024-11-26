package kz.alibek.assignment4.ex2.di

import kz.alibek.assignment4.ex2.ItemViewModel
import kz.alibek.assignment4.ex2.data.RetrofitInstance
import kz.alibek.assignment4.ex2.repository.ItemRepository
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val exercise2Module = module {
    viewModel {
        ItemViewModel(
            repository = get()
        )
    }

    single {
        ItemRepository(
            api = RetrofitInstance.api
        )
    }
}