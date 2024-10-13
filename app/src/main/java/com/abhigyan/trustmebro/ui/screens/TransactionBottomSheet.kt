package com.abhigyan.trustmebro.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.viewModel
import com.abhigyan.trustmebro.calculator.types.Event
import com.abhigyan.trustmebro.calculator.types.Person
import com.abhigyan.trustmebro.calculator.types.Transaction
import com.abhigyan.trustmebro.presentation.TransactionBottomSheetViewModel
import com.abhigyan.trustmebro.ui.utils.CurrencyInput
import com.abhigyan.trustmebro.ui.utils.DropdownTextField
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionBottomSheet(event: Event,onDismiss: ()->Unit){
    val viewModel = viewModel<TransactionBottomSheetViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TransactionBottomSheetViewModel(event) as T
            }
        }
    )
    val bottomSheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true
    )
    val coroutineScope = rememberCoroutineScope()
    val transactionName by viewModel.transactionName
    ModalBottomSheet(onDismissRequest = {
        if(bottomSheetState.isVisible) {
            coroutineScope.launch { bottomSheetState.hide() }
            onDismiss()
            viewModel.reset()
        }
    }, sheetState = bottomSheetState) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(value = transactionName, placeholder = { Text(text = "Transaction Name", style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold))},
                onValueChange = { viewModel.updateName(it)}, singleLine = true,
//                trailingIcon = { Icon(imageVector = Icons.Default.Edit, contentDescription = "edit")},
                textStyle = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                colors = TextFieldDefaults.colors().copy(unfocusedContainerColor = Color.Transparent, focusedContainerColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent),
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences, imeAction = ImeAction.Done),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null
                    )
                },
                modifier = Modifier.fillMaxWidth())
            HorizontalDivider(
                Modifier
                    .width(100.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp)), thickness = 2.dp)
            DynamicListPersonWithAmount(viewModel, people = viewModel.event.people, inputList = viewModel.payersList)
            HorizontalDivider(
                Modifier
                    .width(100.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp)), thickness = 2.dp)
            Text("Add Payees", style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold))
            HorizontalDivider(
                Modifier
                    .width(100.dp)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(8.dp)), thickness = 2.dp)
            Row(Modifier.fillMaxWidth(),horizontalArrangement = Arrangement.SpaceEvenly) {
                ElevatedButton(onClick = {
                    onDismiss()
                    viewModel.reset()
                }) {
                    Text(text = "Cancel")
                }
                Button(onClick = {
                    val transaction =
                        Transaction(transactionName, viewModel.payersList, viewModel.payeesList)
                    viewModel.event.transactions += transaction
                }) {
                    Text(text = "Save")
                }
            }
        }
    }
}

@Composable
fun PersonWithAmountInput(people: List<Person>, inputList: SnapshotStateList<Pair<Person, Double>>, index: Int){
    val peopleList  = people - inputList.map { it.first }.toSet()
    Row(
        Modifier
            .fillMaxSize()
            .height(50.dp)) {
        DropdownTextField(modifier = Modifier.fillMaxWidth(0.5f), options = peopleList, initialValue = inputList[index].first) {
            inputList[index] = inputList[index].copy(first = it)
        }
        Box(modifier = Modifier.width(20.dp))
        CurrencyInput(Modifier.fillMaxWidth()){
            inputList[index] = inputList[index].copy(second = it)
        }
    }
}

@Composable
fun DynamicListPersonWithAmount(viewModel: TransactionBottomSheetViewModel, people: List<Person>, inputList: SnapshotStateList<Pair<Person, Double>>){
    val amountStyle = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.onBackground)
    var totalPeople by remember {
        mutableStateOf("")
    }
    val totalPeopleInt by remember {
        derivedStateOf { totalPeople.toIntOrNull() ?: 0 }
    }


    Column(Modifier.fillMaxWidth()) {
        Text(text = "Payers")
        OutlinedTextField(totalPeople.toString(), onValueChange = { value->
            if (value.matches("^|[1-9]\\d*?$".toRegex())) {
                val intVal = value.toIntOrNull() ?: 0
                if(intVal <= people.size) {
                    totalPeople = value
                    if(totalPeopleInt>0) viewModel.updateListCount(inputList,totalPeopleInt)
                }
            }
        }, textStyle = amountStyle, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Total Number of Payers")})
        Box(modifier = Modifier.height(8.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            itemsIndexed(inputList){i,item->
                PersonWithAmountInput(people = people,inputList= inputList, index = i)
                Box(modifier = Modifier.height(8.dp))
            }
        }
    }
}