package com.nagesh.graphql.domain.repository

import com.nagesh.graphql.FetchContinentsQuery

interface CountryRepo {
    suspend fun getContinents(): Result<FetchContinentsQuery.Data>
}