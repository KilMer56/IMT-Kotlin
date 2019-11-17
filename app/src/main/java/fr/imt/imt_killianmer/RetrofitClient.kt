package fr.imt.imt_killianmer

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofit by lazy {
    val url = "http://henri-potier.xebia.fr/"

    Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(BookService::class.java)
}