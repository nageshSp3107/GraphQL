package com.nagesh.graphql.presentation.continents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nagesh.graphql.ContinentsFetchingQuery
import com.nagesh.graphql.domain.use_cases.FetchContinentsUseCase
import com.nagesh.graphql.presentation.continents.ContinentsScreen.UiState
import com.nagesh.graphql.utils.NetworkResult
import com.nagesh.graphql.utils.NetworkResult.Loading
import com.nagesh.graphql.utils.NetworkResult.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ContinentsViewModel @Inject constructor(
    private val fetchContinentsUseCase: FetchContinentsUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    init {
        getContinents()
    }

    fun getContinents() = fetchContinentsUseCase.invoke()
        .onEach { networkResult ->
            withContext (Dispatchers.Main){
                when (networkResult) {
                    is Loading -> {
                        _uiState.update {
                            UiState(isLoading = true)
                        }
                    }
                    is Success -> {
                        _uiState.update {
                            UiState(data = networkResult.data)
                        }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update {
                            UiState(
                                error = networkResult.message ?: "Unknown Error"
                            )
                        }
                    }

                }
            }
        }
        .launchIn(viewModelScope)

}

object ContinentsScreen{
    data class UiState(
        val isLoading: Boolean = false,
        val error: String = "",
        val data: ContinentsFetchingQuery.Data? = null
    )
}