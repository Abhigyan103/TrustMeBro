package com.abhigyan.trustmebro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.abhigyan.trustmebro.calculator.types.Event
import com.abhigyan.trustmebro.calculator.types.Transaction
import com.abhigyan.trustmebro.presentation.EventsViewModel
//import com.abhigyan.trustmebro.ui.screens.Routes
import com.abhigyan.trustmebro.ui.screens.EventScreen
import com.abhigyan.trustmebro.ui.screens.EventsRoute
import com.abhigyan.trustmebro.ui.screens.MainFeatureRoute
import com.abhigyan.trustmebro.ui.screens.TransactionRoute
import com.abhigyan.trustmebro.ui.screens.TransactionsScreen
import com.abhigyan.trustmebro.ui.theme.TrustMeBroTheme
import com.abhigyan.trustmebro.ui.utils.CustomNavType
import com.abhigyan.trustmebro.ui.utils.sharedViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrustMeBroTheme {
                val navHost = rememberNavController()
                MyNavHost(navController = navHost, startDestination = MainFeatureRoute){
                    navigation<MainFeatureRoute>(
                        startDestination = EventsRoute
                    ){
                        composable<EventsRoute>() {backStackEntry->
                            val viewModel = backStackEntry.sharedViewModel<EventsViewModel>(navController = navHost)
                            EventScreen(navHost, events = viewModel.events, viewModel::addNewEvent)
                        }
                        composable<TransactionRoute>(
                            typeMap = mapOf(
                                typeOf<Int>() to CustomNavType.EventType
                            )
                        ) {backStackEntry->
                            val arguments = backStackEntry.toRoute<TransactionRoute>()
                            val viewModel = backStackEntry.sharedViewModel<EventsViewModel>(navController = navHost)

                            TransactionsScreen(event = viewModel.events[arguments.eventIndex])
                        }
                    }
                }
            }
        }
    }
}