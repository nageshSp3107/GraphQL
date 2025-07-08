package com.nagesh.graphql.presentation.navigation

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.nagesh.graphql.presentation.continents.ContinentsScreen
import com.nagesh.graphql.presentation.continents.ContinentsViewModel
import com.nagesh.graphql.presentation.details.FetchDetails
import com.nagesh.graphql.presentation.details.FetchDetailsViewModel
import kotlinx.serialization.Serializable

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Dest.Home,
    ){

        composable<Dest.Home>{
            val viewModel = hiltViewModel<ContinentsViewModel>()
            ContinentsScreen(viewModel = viewModel){
                navController.navigate(Dest.Details(it))
            }
        }

        composable<Dest.Details> {
            val fetchDetailsViewModel = hiltViewModel<FetchDetailsViewModel>()
            val code = it.toRoute<Dest.Details>().code
            LaunchedEffect(code) {
                fetchDetailsViewModel.fetchDetails(code)
            }
            FetchDetails(fetchDetailsViewModel)
        }
    }
}

@Serializable
sealed class Dest{

    @Serializable
    data object Home: Dest()

    @Serializable
    data class Details(val code: String): Dest()
}

@Preview
@Composable
private fun MainNav() {
    Surface {
        MainNavigation()
    }
}