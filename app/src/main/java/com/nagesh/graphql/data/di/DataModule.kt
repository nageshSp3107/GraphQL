package com.nagesh.graphql.data.di

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.okHttpClient
import com.nagesh.graphql.data.repository.CountryRepoImpl
import com.nagesh.graphql.domain.repository.CountryRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("https://countries.trevorblades.com/")
            .okHttpClient(OkHttpClient.Builder().build())
            .build()
    }


    @Provides
    fun provideCountryRepo(apolloClient: ApolloClient): CountryRepo {
        return CountryRepoImpl(apolloClient)
    }


}