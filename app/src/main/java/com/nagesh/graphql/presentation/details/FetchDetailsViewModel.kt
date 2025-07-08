package com.nagesh.graphql.presentation.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagesh.graphql.domain.use_cases.FetchContinentDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FetchDetailsViewModel @Inject constructor(
    private val fetchContinentDetailsUseCase: FetchContinentDetailsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow<FetchDetails.UiState>(FetchDetails.UiState())
    val uiState: StateFlow<FetchDetails.UiState> get() = _uiState.asStateFlow()

     fun fetchDetails(code: String) {
        _uiState.value = FetchDetails.UiState(isLoading = true)
        fetchContinentDetailsUseCase.invoke(code).onEach {result->
            withContext(Dispatchers.Main) {
                when (result) {
                    is com.nagesh.graphql.utils.NetworkResult.Success -> {
                        _uiState.value = FetchDetails.UiState(data = result.data)
                    }
                    is com.nagesh.graphql.utils.NetworkResult.Error -> {
                        _uiState.value = FetchDetails.UiState(error = result.message!!)
                    }
                    is com.nagesh.graphql.utils.NetworkResult.Loading -> {
                        _uiState.value = FetchDetails.UiState(isLoading = true)
                    }
                }
            }

        }.launchIn(viewModelScope)
    }

}

object FetchDetails{
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: com.nagesh.graphql.FetchDetailsQuery.Data? = null
    )
}