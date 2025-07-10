package com.nagesh.graphql.presentation.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun FetchDetails(fetchDetailsViewModel: FetchDetailsViewModel) {
    val uiState by fetchDetailsViewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            // Show loading indicator
            CircularProgressIndicator()
        }
    }
    if (uiState.error.isNotEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Show loading indicator
            Text(uiState.error)
        }
    }

    uiState.data?.continent?.let { continent ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            stickyHeader {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(5.dp),
                    text = continent.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )
            }
            items(continent.countries) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                ) {
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                    )
                    Text(
                        text = it.name,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                    )
                    Text(
                        text = it.emoji,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                    )
                    Text(
                        text = "Languages: ${it.languages.joinToString()}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(
                        modifier = Modifier
                            .height(5.dp)
                    )
                    HorizontalDivider()
                }

            }

        }
    }
}