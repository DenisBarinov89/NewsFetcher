package com.example.newsfetcher.di

import androidx.room.Room
import com.example.newsfetcher.AppDataBase
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://newsapi.org/"
const val APP_DATABASE = "APP_DATABASE"

val networkModule = module {
    single {
        OkHttpClient.Builder().build()
    } // собираем OkHttpClient - используется паттерн "Builder" (зачем? - кратко:
    // сама делает всяки штуки, по типу "закрыть соединение", "открыть соединение" и пр. )

    single<Retrofit> {
        Retrofit.Builder()  //собираем Retrofit для похода в интернет
            .baseUrl(BASE_URL)                 //передаем URL, куда идем
            .addConverterFactory(GsonConverterFactory.create()) //подключаем конвертер, чтобы полученная JSON-строка по запросу конвертилась в объект
            .client(get()) //подключаем http-клиент (OkhttpClient)
            .build()
    }
}

val dataBaseModule = module {

    single {
        Room
            .databaseBuilder(androidContext(), AppDataBase::class.java, APP_DATABASE)
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<AppDataBase>().bookmarksDao()
    }

}