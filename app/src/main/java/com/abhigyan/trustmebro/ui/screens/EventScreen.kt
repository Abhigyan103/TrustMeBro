package com.abhigyan.trustmebro.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.abhigyan.trustmebro.calculator.types.Bill
import com.abhigyan.trustmebro.calculator.types.Event
import com.abhigyan.trustmebro.calculator.types.Person
import com.abhigyan.trustmebro.calculator.types.Transaction
import com.abhigyan.trustmebro.presentation.EventsViewModel
import com.abhigyan.trustmebro.ui.utils.MyAppBar
import com.abhigyan.trustmebro.ui.widgets.EventItem

fun getSampleEvent() : Event{
    val abhigyan = Person("Abhigyan",6290626866,"6290626866@axl", Bill())
    val spandan = Person("Spandan",6290626867,"6290626867@axl", Bill())
    val pankaj = Person("Pankaj",6290626868,"6290626868@axl", Bill())
    val sandipan = Person("Sandipan",6290626869,"6290626869@axl", Bill())
    val event = Event("Bhutan Trip", transactions = mutableListOf(
        Transaction("Chai", mutableListOf(Pair(abhigyan,10.0),Pair(spandan,30.0)),
            mutableListOf(
                Pair(abhigyan,10.0),
                Pair(spandan, 10.0),
                Pair(sandipan,10.0),
                Pair(pankaj,10.0))
    ),
        Transaction("Toto",
            mutableListOf(Pair(spandan,80.0)), mutableListOf(Pair(abhigyan,10.0),Pair(spandan, 50.0), Pair(sandipan,10.0),Pair(pankaj,10.0))
        )
    ), people = mutableListOf(abhigyan,spandan,pankaj,sandipan)
    )
    return event
}

@Composable
fun EventScreen(navController: NavHostController, events: List<Event>, addNewEvent:(Event)->Unit) {
    val context = LocalContext.current
    Scaffold(
        topBar = { MyAppBar(title = "Events") },
        floatingActionButton = { FloatingActionButton(onClick = {
            addNewEvent(getSampleEvent())
//            val intent =getPaymentIntent("rohiniafsana@axl","Khush raho",2.0)
//            var response = context.startActivity(intent)

        }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add new event")
        }}
    ) { innerPadding->
        if(events.isEmpty()){
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "You have no events. Create your first event.")
            }
        }else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                itemsIndexed(events){i,it->
                    EventItem(event = it, onClick ={
                        navController.navigate(TransactionRoute(i))
                    }, onHold={})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EventScreenPreview() {
    val navHost = rememberNavController()
    EventScreen(navHost, listOf(getSampleEvent())) {}
}