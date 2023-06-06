package com.example.notes.di

import com.example.notes.api.AuthInterceptor
import com.example.notes.api.NotesApi
import com.example.notes.api.UserApi
import com.example.notes.utils.Constants
import com.example.notes.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit.Builder {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Singleton
    @Provides
    fun providesUserAPI(retrofitBuilder: Retrofit.Builder): UserApi {
        return retrofitBuilder.build().create(UserApi ::class.java)
    }

    @Singleton
    @Provides
    fun providesNoteAPI(retrofitBuilder: Retrofit.Builder, okHttpClient: OkHttpClient): NotesApi {
        return retrofitBuilder.client(okHttpClient).build().create(NotesApi::class.java)
    }


}