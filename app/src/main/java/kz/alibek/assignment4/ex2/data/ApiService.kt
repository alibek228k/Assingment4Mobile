package kz.alibek.assignment4.ex2.data

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    suspend fun getItems(): List<Item>
}