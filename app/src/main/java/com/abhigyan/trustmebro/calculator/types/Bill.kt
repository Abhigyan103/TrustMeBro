package com.abhigyan.trustmebro.calculator.types

import kotlinx.serialization.Serializable

@Serializable
data class Bill(var items: List<Item> = emptyList(), var totalSpent: Double = 0.0)