package com.nagesh.graphql.presentation.continents

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ContinentsScreen(modifier: Modifier = Modifier,
                     viewModel: ContinentsViewModel,
                     onClick: (String) -> Unit) {
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
        val grouped = list.groupBy { it.name.first().toString() }
        LazyColumn(
            modifier =
                modifier
                    .fillMaxSize()
                    .padding(12.dp),
        )
        {
            grouped.forEach { key, value ->
                stickyHeader {
                    Box(
                        modifier  = Modifier.fillMaxWidth()
                            .padding(10.dp)
                            .background(Color.LightGray),
                    ) {
                        Text(
                            modifier  = Modifier.fillMaxWidth()
                                .padding(10.dp),
                            text = key,
                            style = MaterialTheme.typography.headlineMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
                items(value) {it->

                    Card(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                            .clickable{
                                onClick(it.code)
                            }
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
}