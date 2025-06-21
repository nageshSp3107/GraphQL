package com.nagesh.graphql.data.repository

import com.apollographql.apollo.ApolloClient
import com.nagesh.graphql.FetchContinentsQuery
import com.nagesh.graphql.domain.repository.CountryRepo

class CountryRepoImpl(private val apolloClient: ApolloClient) : CountryRepo {
    override suspend fun getContinents(): Result<FetchContinentsQuery.Data> {
        return try {
            val response = apolloClient.query(FetchContinentsQuery()).execute()
            response.data?.let {
                return Result.success(it)
            } ?: run {
                return Result.failure(Exception("No data found"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }
}