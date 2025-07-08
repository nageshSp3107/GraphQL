package com.nagesh.graphql.presentation.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FetchDetails(fetchDetailsViewModel: FetchDetailsViewModel) {
    val uiState by fetchDetailsViewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Show loading indicator
            CircularProgressIndicator()
        }
    }
    if (uiState.error.isNotEmpty()){
        Box(modifier = Modifier.fillMaxSize()) {
            // Show loading indicator
            Text(uiState.error)
        }
    }

    uiState.data?.continent?.let {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Continent Name: ${it.name}",
                style = MaterialTheme.typography.headlineMedium
            )
            it.countries.forEach{ country ->
                Text(
                    text = "Country Name: ${country.name}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Country Code: ${country.code}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Country Languages: ${country.languages}",
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}