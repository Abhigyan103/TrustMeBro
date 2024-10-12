package com.abhigyan.trustmebro.ui.screens

import com.abhigyan.trustmebro.calculator.types.Event
import kotlinx.serialization.Serializable

@Serializable
data object MainFeatureRoute

@Serializable
data object EventsRoute

@Serializable
data class TransactionRoute(
    val eventIndex: Int
)