package com.abhigyan.trustmebro.calculator.types

import kotlinx.serialization.Serializable

@Serializable
class Transaction(var name: String="",
                  val payers: List<Pair<Person,Double>>,
                  val payees: List<Pair<Person,Double>>,
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