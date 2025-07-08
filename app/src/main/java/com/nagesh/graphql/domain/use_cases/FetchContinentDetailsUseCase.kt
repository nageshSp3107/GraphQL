package com.nagesh.graphql.domain.use_cases

import com.nagesh.graphql.FetchDetailsQuery
import com.nagesh.graphql.domain.repository.CountryRepo
import com.nagesh.graphql.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchContinentDetailsUseCase @Inject constructor(private val countryRepo: CountryRepo) {

    operator fun invoke(code: String): Flow<NetworkResult<FetchDetailsQuery.Data>> = flow<NetworkResult<FetchDetailsQuery.Data>> {
        emit(NetworkResult.Loading())
        val response = countryRepo.getContinentDetails(code)
        if (response.isSuccess) {
            emit(NetworkResult.Success(response.getOrThrow()))
        } else {
            emit(NetworkResult.Error(response.exceptionOrNull()?.message ?: "Unknown Error"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message ?: "Unknown Error"))
    }
        .flowOn(Dispatchers.IO)
}