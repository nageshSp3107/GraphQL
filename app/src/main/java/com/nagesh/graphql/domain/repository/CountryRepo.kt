package com.nagesh.graphql.domain.repository

import com.nagesh.graphql.ContinentsFetchingQuery

interface CountryRepo {
    suspend fun getContinents(): Result<ContinentsFetchingQuery.Data>
}