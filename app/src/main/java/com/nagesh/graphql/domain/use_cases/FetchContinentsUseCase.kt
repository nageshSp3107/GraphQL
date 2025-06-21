package com.nagesh.graphql.domain.use_cases

import com.nagesh.graphql.FetchContinentsQuery
import com.nagesh.graphql.domain.repository.CountryRepo
import com.nagesh.graphql.utils.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class FetchContinentsUseCase @Inject constructor(
    private val countryRepo: CountryRepo
){
    operator fun invoke() : Flow<NetworkResult<FetchContinentsQuery.Data>> = flow {
        emit(NetworkResult.Loading())
        val response = countryRepo.getContinents()
        if (response.isSuccess){
            emit(NetworkResult.Success(response.getOrThrow()))
        }else{
            emit(NetworkResult.Error(response.exceptionOrNull()?.message ?: "Unknown Error"))
        }
    }.catch {
        emit(NetworkResult.Error(it.message ?: "Unknown Error"))
    }
        .flowOn(Dispatchers.IO)
}