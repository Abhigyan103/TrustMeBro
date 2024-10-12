package com.abhigyan.trustmebro.calculator.types

import com.abhigyan.trustmebro.ui.screens.EventsRoute
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Serializable
class Event(var name: String,
            val date: String = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
            var transactions: MutableList<Transaction> = mutableListOf(),
            var people: MutableList<Person> = mutableListOf(),
) {
    var totalSpent: Double = 0.0
        get() =transactions.sumOf { it.totalCost }
        private set
    fun calc(){
        /* TODO */
    }
    fun addTransaction(transaction: Transaction){
        transactions.add(transaction)
    }

    fun deleteTransaction(index: Int){
        transactions.removeAt(index)
    }

    fun copy(): Event{
        return Event(name,date,transactions,people)
    }
}