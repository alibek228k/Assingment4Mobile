package kz.alibek.assignment4.ex2.repository

import kz.alibek.assignment4.ex2.data.ApiService
import kz.alibek.assignment4.ex2.data.Item

class ItemRepository(private val api: ApiService) {

    suspend fun fetchItems(): List<Item> {
        return api.getItems()
    }
}