package com.nagesh.graphql.data.repository

import com.apollographql.apollo.ApolloClient
import com.nagesh.graphql.ContinentsFetchingQuery
import com.nagesh.graphql.domain.repository.CountryRepo

class CountryRepoImpl(private val apolloClient: ApolloClient) : CountryRepo {
    override suspend fun getContinents(): Result<ContinentsFetchingQuery.Data> {
        return try {
            val response = apolloClient.query(ContinentsFetchingQuery()).execute()
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