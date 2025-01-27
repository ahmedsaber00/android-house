package com.atef.clubhouse.di

import android.content.Context
import com.atef.clubhouse.BuildConfig
import com.atef.clubhouse.data.base.RetrofitFactory
import com.atef.clubhouse.data.feature.auth.AuthLocalDataSource
import com.atef.clubhouse.data.remote.feature.auth.service.AuthApiHandler
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object NetworkModule {

    @[Provides Singleton]
    fun provideBaseURL(): String = BuildConfig.BASE_URL

    @[Provides Singleton]
    fun provideApiHandler(
        baseUrl: String,
        @ApplicationContext context: Context,
        authLocalDataSource: AuthLocalDataSource,
    ): AuthApiHandler {
        return RetrofitFactory.makeServiceHandler(
            baseUrl,
            AuthApiHandler::class.java,
            BuildConfig.DEBUG,
            context,
            authLocalDataSource
        ) as AuthApiHandler
    }

    @[Provides Singleton]
    fun provideGson(): Gson = GsonBuilder()
        .setLenient()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        .create()
}
