package com.nagesh.graphql.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.nagesh.graphql.presentation.continents.ContinentsViewModel

@Composable
fun ContinentsScreen(modifier: Modifier = Modifier, viewModel: ContinentsViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading){
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Show loading indicator
            CircularProgressIndicator()
        }
    }
    if (uiState.error.isNotEmpty()){
        Box(modifier = modifier.fillMaxSize()) {
            // Show loading indicator
            Text(uiState.error)
        }
    }
    uiState.data?.continents?.let { list->
        LazyColumn(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(12.dp),
        ) {
            items(list) {it->
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                    ) {
                        Text(
                            text = it.name,
                            style = MaterialTheme.typography.headlineSmall,
                        )
                        Text(
                            text = it.code,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }



}