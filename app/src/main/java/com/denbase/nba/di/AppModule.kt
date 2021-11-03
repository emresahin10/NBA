package com.denbase.nba.dicomp

import com.denbase.nba.api.BallDontLieApi
import com.denbase.nba.repositories.BallDontLieRepository
import com.denbase.nba.repositories.BaseBallDontLieRepository
import com.denbase.nba.utils.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor()
        .apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .baseUrl(BASE_URL)
        .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): BallDontLieApi = retrofit.create(BallDontLieApi::class.java)

    @Singleton
    @Provides
    fun providesRepository(api: BallDontLieApi) = BaseBallDontLieRepository(api) as BallDontLieRepository

}