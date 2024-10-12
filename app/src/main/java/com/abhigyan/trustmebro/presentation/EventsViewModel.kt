package com.abhigyan.trustmebro.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.abhigyan.trustmebro.calculator.types.Event
import com.abhigyan.trustmebro.data.EventsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EventsViewModel(
    private val eventsRepository: EventsRepository
) : ViewModel(){
    var events by mutableStateOf(listOf<Event>())
        private set

    fun addNewEvent(event: Event){
        events += event
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