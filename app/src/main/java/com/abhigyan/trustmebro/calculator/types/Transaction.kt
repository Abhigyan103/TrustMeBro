package com.abhigyan.trustmebro.calculator.types

import kotlinx.serialization.Serializable

@Serializable
class Transaction(var name: String="",
                  val payers: MutableList<Pair<Person,Double>> = mutableListOf(),
                  val payees: MutableList<Pair<Person,Double>> = mutableListOf(),
) {
    var totalCost: Double = 0.0
        get() = payers.sumOf { it.second }
        private set

    fun calcSplit(){
        /* TODO */
    }
    fun addPayees(){
        /* TODO */
    }
}