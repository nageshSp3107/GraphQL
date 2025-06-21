package com.nagesh.graphql

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.nagesh.graphql.presentation.continents.ContinentsScreen
import com.nagesh.graphql.presentation.continents.ContinentsViewModel
import com.nagesh.graphql.presentation.screens.ContinentsScreen
import com.nagesh.graphql.ui.theme.GrapghqlAppTheme
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrapghqlAppTheme {
                val continentsViewModel = hiltViewModel<ContinentsViewModel>()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ContinentsScreen(viewModel = continentsViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GrapghqlAppTheme {
        Greeting("Android")
    }
}