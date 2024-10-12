package com.abhigyan.trustmebro.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.abhigyan.trustmebro.calculator.types.Event
import com.abhigyan.trustmebro.calculator.types.Person
import com.abhigyan.trustmebro.calculator.types.Transaction
import com.abhigyan.trustmebro.data.EventsRepository

val EventKey = object : CreationExtras.Key<Event> {}

class TransactionBottomSheetViewModel(val event: Event): ViewModel() {
    var transactionName = mutableStateOf("")
    private set
    val payersList = mutableStateListOf<Pair<Person,Double>>()
    val payeesList = mutableStateListOf<Pair<Person,Double>>()
    fun updateName(newName : String){
        transactionName.value = newName
    }
    fun updateListCount(list: SnapshotStateList<Pair<Person, Double>>, count: Int){
        if(list.size > count){
            while (list.size!=count){
                list.removeLast()
            }
        }else if(list.size < count) {
            val addedPeople = list.map { it.first }.toSet()
            val remainingPeople = event.people.subtract(addedPeople)
            while (list.size!=count){
                val person = remainingPeople.random()
                list.add(Pair(person,0.0))
                remainingPeople.minus(person)
            }
        }
    }
}