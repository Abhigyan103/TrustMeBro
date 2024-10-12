package com.abhigyan.trustmebro.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.abhigyan.trustmebro.calculator.types.Event
import com.abhigyan.trustmebro.calculator.types.Transaction
import com.abhigyan.trustmebro.ui.utils.MyAppBar
import com.abhigyan.trustmebro.ui.widgets.TransactionItem


@Composable
fun TransactionsScreen(event: Event) {
    var newEvent by remember {
        mutableStateOf(event)
    }
    val transactionsSize by remember{ derivedStateOf { newEvent.transactions.size } }
    val showSheet = remember { mutableStateOf(false) }
    Scaffold(
        topBar = { MyAppBar(title = "Transactions") },
        floatingActionButton = { FloatingActionButton(onClick = {
            showSheet.value=true
        }, containerColor = MaterialTheme.colorScheme.primary) {
            Row(Modifier.padding(8.dp,0.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "New transaction")
                Text("New transaction")
            }
        }}
    ) { innerPadding->
        if(showSheet.value) {
            TransactionBottomSheet(showSheet, event = event)
        }
        if(transactionsSize==0){
            Box(modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(), contentAlignment = Alignment.Center){
                Text(text = "You have no transactions.")
            }
        }else {
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                itemsIndexed(newEvent.transactions){i, it->
                        TransactionItem(transaction = it, onDelete={
                            newEvent.transactions-=it
                        })
                }
            }
        }
    }
}

@Preview
@Composable
fun TransactionsScreenPreview() {
    val event =Event("Bhutan")
    event.transactions.add(Transaction("Transaction 1"))
    event.transactions.add(Transaction("Transaction 2"))
    TransactionsScreen(event)
}