package com.nagesh.graphql.domain.repository

import com.nagesh.graphql.ContinentsFetchingQuery
import com.nagesh.graphql.FetchDetailsQuery

interface CountryRepo {
    suspend fun getContinents(): Result<ContinentsFetchingQuery.Data>

    suspend fun getContinentDetails(code:String): Result<FetchDetailsQuery.Data>
}