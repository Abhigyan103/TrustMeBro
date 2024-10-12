package com.abhigyan.trustmebro.calculator.types

import kotlinx.serialization.Serializable

@Serializable
data class Item(var name: String, var price: Double)