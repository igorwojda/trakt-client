package com.igorwojda.traktclient.data.dagger

import com.igorwojda.traktclient.data.repository.trakt.TraktRemoteRepository
import com.igorwojda.traktclient.data.repository.trakt.retrofit.TraktAuthenticator
import com.igorwojda.traktclient.data.repository.trakt.retrofit.TraktGsonHelper
import com.igorwojda.traktclient.data.repository.trakt.retrofit.TraktRequestInterceptor
import com.igorwojda.traktclient.data.repository.trakt.service.TraktRetrofitService
import dagger.Binds
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

abstract class DataModule @Inject constructor() {

    @Binds
    abstract fun bindsTraktRemoteRepository(trackRepository: TraktRemoteRepository)

    @Provides
    @Singleton
    fun provideTraktRetrofitService(retrofit: Retrofit): TraktRetrofitService {
        return retrofit.create(TraktRetrofitService::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(TraktRemoteRepository.API_URL)
                .addConverterFactory(GsonConverterFactory.create(TraktGsonHelper.createGson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addNetworkInterceptor(TraktRequestInterceptor())
                .authenticator(TraktAuthenticator())
                .connectTimeout(3, TimeUnit.SECONDS)
                .build()
    }
}