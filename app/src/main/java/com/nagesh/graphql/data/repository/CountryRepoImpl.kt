package com.nagesh.graphql.data.repository

import com.apollographql.apollo.ApolloClient
import com.nagesh.graphql.ContinentsFetchingQuery
import com.nagesh.graphql.FetchDetailsQuery
import com.nagesh.graphql.domain.repository.CountryRepo

class CountryRepoImpl(private val apolloClient: ApolloClient) : CountryRepo {
    override suspend fun getContinents(): Result<ContinentsFetchingQuery.Data> {
        return try {
            val response = apolloClient.query(ContinentsFetchingQuery()).execute()
            response.data?.let {
                return Result.success(it)
            } ?: run {
                return Result.failure(response.exception!!)
            }
        }catch (e: Exception){
            Result.failure(e)
        }
    }

    override suspend fun getContinentDetails(code: String): Result<FetchDetailsQuery.Data> {
        return try {
            val result = apolloClient.query(FetchDetailsQuery(code)).execute()
            result.data?.let {
                 Result.success(it)
            } ?: run {
                 Result.failure(result.exception!!)
            }
        }catch (e: Exception) {
             Result.failure(e)
        }
    }
}