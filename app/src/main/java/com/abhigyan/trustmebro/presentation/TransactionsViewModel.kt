package com.abhigyan.trustmebro.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.abhigyan.trustmebro.calculator.types.Event
import com.abhigyan.trustmebro.calculator.types.Transaction
import com.abhigyan.trustmebro.data.EventsRepository
import com.abhigyan.trustmebro.data.TransactionsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class TransactionsViewModel(
    private val transactionsRepository: TransactionsRepository
): ViewModel() {

    var transactions = mutableListOf<Transaction>()
        private set

    fun addNewTransaction(event: Event){
//        _events.update {
//
//        }
    }
    fun deleteEvent(){
        /* TODO */
    }

    companion object{
        val Factory: ViewModelProvider.Factory= viewModelFactory {
            initializer {
                EventsViewModel(EventsRepository())
            }
        }
    }
}